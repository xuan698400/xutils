package com.xuan.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Provides support for localization in XWork.
 * <p/>
 * <!-- START SNIPPET: searchorder --> Resource bundles are searched in the following order:
 * <p/>
 * <p/>
 * <ol>
 * <li>ActionClass.properties</li>
 * <li>package.properties (of the directory where class is located and every parent directory all the way to the root
 * directory,必须在一个包下)</li>
 * <li>global resource properties</li>
 * </ol>
 * <p/>
 * <!-- END SNIPPET: searchorder -->
 * <p/>
 * <!-- START SNIPPET: packagenote --> To clarify #5, while traversing the package hierarchy, Struts 2 will look for a
 * file package.properties:
 * <p/>
 * com/<br/>
 * &nbsp; acme/<br/>
 * &nbsp; &nbsp; package.properties<br/>
 * &nbsp; &nbsp; actions/<br/>
 * &nbsp; &nbsp; &nbsp; package.properties<br/>
 * &nbsp; &nbsp; &nbsp; FooAction.java<br/>
 * &nbsp; &nbsp; &nbsp; FooAction.properties<br/>
 * <p/>
 * If FooAction.properties does not exist, com/acme/action/package.properties will be searched for, if not found
 * com/acme/package.properties, if not found com/package.properties, etc.
 * <p/>
 * <!-- END SNIPPET: packagenote -->
 * <p/>
 * <!-- START SNIPPET: globalresource --> A global resource bundle could be specified programatically, as well as the
 * locale.
 * <p/>
 * <!-- END SNIPPET: globalresource -->
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:21:08 $
 */
public class LocalizedTextUtil {
    private static final List<String> DEFAULT_RESOURCE_BUNDLES = new CopyOnWriteArrayList<String>();
    private static boolean reloadBundles = false;
    /**
     * 国际化资源文件的编码格式
     */
    private static Charset charsetOfBundles = Charset.defaultCharset();
    private static final ResourceBundle EMPTY_BUNDLE = new EmptyResourceBundle();
    private static final ConcurrentMap<String, ResourceBundle> bundlesMap = new ConcurrentHashMap<String, ResourceBundle>();
    private static final Map<MessageFormatKey, MessageFormat> messageFormats = new HashMap<MessageFormatKey, MessageFormat>();

    private static ClassLoader delegatedClassLoader;

    static {
        clearDefaultResourceBundles();
    }

    /**
     * 清理全局资源文件
     */
    public static void clearDefaultResourceBundles() {
        if (DEFAULT_RESOURCE_BUNDLES != null) {
            synchronized (DEFAULT_RESOURCE_BUNDLES) {
                DEFAULT_RESOURCE_BUNDLES.clear();
                // DEFAULT_RESOURCE_BUNDLES.add("net/zdsoft/keel/keel-messages");
            }
        }
        else {
            // synchronized (DEFAULT_RESOURCE_BUNDLES) {
            // DEFAULT_RESOURCE_BUNDLES.add("net/zdsoft/keel/keel-messages");
            // }
        }
    }

    /**
     * 设置是否允许重新加载资源文件
     * 
     * @param reloadBundles
     *            reload bundles?
     */
    public static void setReloadBundles(boolean reloadBundles) {
        LocalizedTextUtil.reloadBundles = reloadBundles;
    }

    /**
     * 设置国际化资源文件的编码格式
     * 
     * @param charsetOfBundles
     *            charsetOfBundles
     */
    public static void setCharsetOfBundles(Charset charsetOfBundles) {
        LocalizedTextUtil.charsetOfBundles = charsetOfBundles;
    }

    /**
     * 添加全局的资源文件
     * <p/>
     * 如果资源文件已经存在于列表中，会重新添加
     * 
     * @param resourceBundleName
     *            the name of the bundle to add.
     */
    public static void addDefaultResourceBundle(String resourceBundleName) {
        // make sure this doesn't get added more than once
        synchronized (DEFAULT_RESOURCE_BUNDLES) {
            DEFAULT_RESOURCE_BUNDLES.remove(resourceBundleName);
            DEFAULT_RESOURCE_BUNDLES.add(0, resourceBundleName);
        }

    }

    /**
     * 取得全局的信息 Returns a localized message for the specified key, aTextName. Neither the key nor the message is
     * evaluated.
     * 
     * @param aTextName
     *            the message key
     * @param locale
     *            the locale the message should be for
     * @return a localized message based on the specified key, or null if no localized message can be found for it
     */
    public static String findDefaultText(String aTextName, Locale locale) {
        List<String> localList = DEFAULT_RESOURCE_BUNDLES;

        for (String bundleName : localList) {
            ResourceBundle bundle = findResourceBundle(bundleName, locale);
            if (bundle != null) {
                reloadBundles();
                try {
                    return getEncodedString(bundle.getString(aTextName));
                }
                catch (MissingResourceException e) {
                    // ignore and try others
                }
            }
        }

        return null;
    }

    /**
     * 取得全局的信息 Returns a localized message for the specified key, aTextName, substituting variables from the array of
     * params into the message. Neither the key nor the message is evaluated.
     * 
     * @param aTextName
     *            the message key
     * @param locale
     *            the locale the message should be for
     * @param params
     *            an array of objects to be substituted into the message text
     * @return A formatted message based on the specified key, or null if no localized message can be found for it
     */
    public static String findDefaultText(String aTextName, Locale locale, Object[] params) {
        String defaultText = findDefaultText(aTextName, locale);
        if (defaultText != null) {
            MessageFormat mf = buildMessageFormat(defaultText, locale);
            return mf.format(params);
        }
        return null;
    }

    /**
     * Finds the given resorce bundle by it's name.
     * <p/>
     * Will use <code>Thread.currentThread().getContextClassLoader()</code> as the classloader. If
     * {@link #delegatedClassLoader} is defined and the bundle cannot be found the current classloader it will delegate
     * to that.
     * 
     * @param aBundleName
     *            the name of the bundle (usually it's FQN classname).
     * @param locale
     *            the locale.
     * @return the bundle, <tt>null</tt> if not found.
     */
    public static ResourceBundle findResourceBundle(String aBundleName, Locale locale) {
        String key = createMissesKey(aBundleName, locale);

        ResourceBundle bundle;

        try {
            if (!bundlesMap.containsKey(key)) {
                bundle = ResourceBundle.getBundle(aBundleName, locale, Thread.currentThread().getContextClassLoader());
                bundlesMap.put(key, bundle);
            }

            bundle = bundlesMap.get(key);
        }
        catch (MissingResourceException ex) {
            if (delegatedClassLoader != null) {
                try {
                    if (!bundlesMap.containsKey(key)) {
                        bundle = ResourceBundle.getBundle(aBundleName, locale, delegatedClassLoader);
                        bundlesMap.put(key, bundle);
                    }

                    bundle = bundlesMap.get(key);

                }
                catch (MissingResourceException e) {
                    bundle = EMPTY_BUNDLE;
                    bundlesMap.put(key, bundle);
                }
            }
            else {
                bundle = EMPTY_BUNDLE;
                bundlesMap.put(key, bundle);
            }
        }
        return (bundle == EMPTY_BUNDLE) ? null : bundle;
    }

    /**
     * Sets a {@link ClassLoader} to look up the bundle from if none can be found on the current thread's classloader
     * 
     * @param classLoader
     */
    public static void setDelegatedClassLoader(final ClassLoader classLoader) {
        synchronized (bundlesMap) {
            delegatedClassLoader = classLoader;
        }
    }

    /**
     * Removes the bundle from any cached "misses"
     * 
     * @param bundleName
     */
    public static void clearBundle(final String bundleName) {
        synchronized (bundlesMap) {
            bundlesMap.remove(bundleName);
        }
    }

    /**
     * Creates a key to used for lookup/storing in the bundle misses cache.
     * 
     * @param aBundleName
     *            the name of the bundle (usually it's FQN classname).
     * @param locale
     *            the locale.
     * @return the key to use for lookup/storing in the bundle misses cache.
     */
    private static String createMissesKey(String aBundleName, Locale locale) {
        return aBundleName + "_" + locale.toString();
    }

    /**
     * Calls {@link #findText(Class aClass, String aTextName, Locale locale, String defaultMessage, Object[] args)} with
     * aTextName as the default message.
     * 
     * @see #findText(Class aClass, String aTextName, Locale locale, String defaultMessage, Object[] args)
     */
    public static String findText(Class<?> aClass, String aTextName, Locale locale) {
        return findText(aClass, aTextName, locale, aTextName, new Object[0]);
    }

    /**
     * Finds a localized text message for the given key, aTextName. Both the key and the message itself is evaluated as
     * required. The following algorithm is used to find the requested message:
     * <p/>
     * <ol>
     * <li>Look for message in aClass' class hierarchy.
     * <ol>
     * <li>Look for the message in a resource bundle for aClass</li>
     * <li>If not found, traverse up the Class' hierarchy and repeat from the first sub-step</li>
     * </ol>
     * </li>
     * <li>If not found, look for message in child property. This is determined by evaluating the message key as an OGNL
     * expression. For example, if the key is <i>user.address.state</i>, then it will attempt to see if "user" can be
     * resolved into an object. If so, repeat the entire process fromthe beginning with the object's class as aClass and
     * "address.state" as the message key.</li>
     * <li>If not found, look for the message in aClass' package hierarchy.</li>
     * <li>If still not found, look for the message in the default resource bundles.</li>
     * <li>Return defaultMessage</li>
     * </ol>
     * <p/>
     * When looking for the message, if the key indexes a collection (e.g. user.phone[0]) and a message for that
     * specific key cannot be found, the general form will also be looked up (i.e. user.phone[*]).
     * <p/>
     * If a message is found, it will also be interpolated. Anything within <code>${...}</code> will be treated as an
     * OGNL expression and evaluated as such.
     * <p/>
     * If a message is <b>not</b> found a WARN log will be logged.
     * 
     * @param aClass
     *            the class whose name to use as the start point for the search
     * @param aTextName
     *            the key to find the text message for
     * @param locale
     *            the locale the message should be for
     * @param defaultMessage
     *            the message to be returned if no text message can be found in any resource bundle
     * @return the localized text, or null if none can be found and no defaultMessage is provided
     */
    public static String findText(Class<?> aClass, String aTextName, Locale locale, String defaultMessage, Object[] args) {
        if (aTextName == null) {
            aTextName = "";
        }

        // search up class hierarchy
        String msg = findMessage(aClass, aTextName, locale, args);

        if (msg != null) {
            return msg;
        }

        // nothing still? alright, search the package hierarchy now
        for (Class<?> clazz = aClass; (clazz != null) && !clazz.equals(Object.class); clazz = clazz.getSuperclass()) {

            String basePackageName = clazz.getName();
            while (basePackageName.lastIndexOf('.') != -1) {
                basePackageName = basePackageName.substring(0, basePackageName.lastIndexOf('.'));
                String packageName = basePackageName + ".package";
                msg = getMessage(packageName, locale, aTextName, args);

                if (msg != null) {
                    return msg;
                }
            }
        }

        // get default
        GetDefaultMessageReturnArg result = null;
        result = getDefaultMessage(aTextName, locale, args, defaultMessage);

        // could we find the text, if not log a warn
        if (unableToFindTextForKey(result)) {
        }

        return result != null ? result.message : null;
    }

    /**
     * Determines if we found the text in the bundles.
     * 
     * @param result
     *            the result so far
     * @return <tt>true</tt> if we could <b>not</b> find the text, <tt>false</tt> if the text was found (=success).
     */
    private static boolean unableToFindTextForKey(GetDefaultMessageReturnArg result) {
        if (result == null || result.message == null) {
            return true;
        }

        // did we find it in the bundle, then no problem?
        if (result.foundInBundle) {
            return false;
        }

        // not found in bundle
        return true;
    }

    /**
     * Finds a localized text message for the given key, aTextName, in the specified resource bundle with aTextName as
     * the default message.
     * <p/>
     * If a message is found, it will also be interpolated. Anything within <code>${...}</code> will be treated as an
     * OGNL expression and evaluated as such.
     * 
     * @see #findText(java.util.ResourceBundle, String, java.util.Locale, String, Object[])
     */
    public static String findText(ResourceBundle bundle, String aTextName, Locale locale) {
        return findText(bundle, aTextName, locale, aTextName, new Object[0]);
    }

    /**
     * Finds a localized text message for the given key, aTextName, in the specified resource bundle.
     * <p/>
     * If a message is found, it will also be interpolated. Anything within <code>${...}</code> will be treated as an
     * OGNL expression and evaluated as such.
     * <p/>
     * If a message is <b>not</b> found a WARN log will be logged.
     * 
     * @param bundle
     *            the bundle
     * @param aTextName
     *            the key
     * @param locale
     *            the locale
     * @param defaultMessage
     *            the default message to use if no message was found in the bundle
     * @param args
     *            arguments for the message formatter.
     */
    public static String findText(ResourceBundle bundle, String aTextName, Locale locale, String defaultMessage,
            Object[] args) {
        try {
            reloadBundles();

            String message = getEncodedString(bundle.getString(aTextName));
            MessageFormat mf = buildMessageFormat(message, locale);

            return mf.format(args);
        }
        catch (MissingResourceException ex) {
            // ignore
        }

        GetDefaultMessageReturnArg result = getDefaultMessage(aTextName, locale, args, defaultMessage);
        if (unableToFindTextForKey(result)) {
        }
        return result != null ? result.message : null;
    }

    /**
     * Gets the default message.
     */
    private static GetDefaultMessageReturnArg getDefaultMessage(String key, Locale locale, Object[] args,
            String defaultMessage) {
        GetDefaultMessageReturnArg result = null;
        boolean found = true;

        if (key != null) {
            String message = findDefaultText(key, locale);

            if (message == null) {
                message = defaultMessage;
                found = false; // not found in bundles
            }

            // defaultMessage may be null
            if (message != null) {
                MessageFormat mf = buildMessageFormat(message, locale);

                String msg = mf.format(args);
                result = new GetDefaultMessageReturnArg(msg, found);
            }
        }

        return result;
    }

    /**
     * Gets the message from the named resource bundle.
     */
    private static String getMessage(String bundleName, Locale locale, String key, Object[] args) {
        ResourceBundle bundle = findResourceBundle(bundleName, locale);
        if (bundle == null) {
            return null;
        }

        reloadBundles();

        try {
            String message = getEncodedString(bundle.getString(key));
            MessageFormat mf = buildMessageFormat(message, locale);
            return mf.format(args);
        }
        catch (MissingResourceException e) {
            return null;
        }
    }

    private static MessageFormat buildMessageFormat(String pattern, Locale locale) {
        MessageFormatKey key = new MessageFormatKey(pattern, locale);
        MessageFormat format = null;
        synchronized (messageFormats) {
            format = messageFormats.get(key);
            if (format == null) {
                format = new MessageFormat(pattern);
                format.setLocale(locale);
                format.applyPattern(pattern);
                messageFormats.put(key, format);
            }
        }

        return format;
    }

    /**
     * Traverse up class hierarchy looking for message.
     */
    private static String findMessage(Class<?> clazz, String key, Locale locale, Object[] args) {
        // look in properties of this class
        String msg = getMessage(clazz.getName(), locale, key, args);

        if (msg != null) {
            return msg;
        }

        return null;
    }

    /**
     * 转换字符串编码
     * 
     * @param str
     * @return
     */
    private static String getEncodedString(String str) {
        return new String(str.getBytes(Charset.forName("ISO8859-1")), charsetOfBundles);
    }

    /**
     * 清理资源文件和tomcat缓存
     */
    private static void reloadBundles() {
        if (reloadBundles) {
            try {
                clearMap(ResourceBundle.class, null, "cacheList");

                // now, for the true and utter hack, if we're running in tomcat, clear
                // it's class loader resource cache as well.
                clearTomcatCache();
            }
            catch (Exception e) {
            }
        }
    }

    private static void clearTomcatCache() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        // no need for compilation here.
        Class<?> cl = loader.getClass();

        try {
            if ("org.apache.catalina.loader.WebappClassLoader".equals(cl.getName())) {
                clearMap(cl, loader, "resourceEntries");
            }
            else {
            }
        }
        catch (Exception e) {
        }
    }

    private static void clearMap(Class<?> cl, Object obj, String name) throws NoSuchFieldException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field field = cl.getDeclaredField(name);
        field.setAccessible(true);

        Object cache = field.get(obj);

        synchronized (cache) {
            Class<?> ccl = cache.getClass();
            Method clearMethod = ccl.getMethod("clear");
            clearMethod.invoke(cache);
        }

    }

    /**
     * 重置所有状态和缓存。一般测试时使用
     */
    public static void reset() {
        clearDefaultResourceBundles();

        bundlesMap.clear();

        synchronized (messageFormats) {
            messageFormats.clear();
        }
    }

    static class MessageFormatKey {
        String pattern;
        Locale locale;

        MessageFormatKey(String pattern, Locale locale) {
            this.pattern = pattern;
            this.locale = locale;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MessageFormatKey)) {
                return false;
            }

            final MessageFormatKey messageFormatKey = (MessageFormatKey) o;

            if (locale != null ? !locale.equals(messageFormatKey.locale) : messageFormatKey.locale != null) {
                return false;
            }
            if (pattern != null ? !pattern.equals(messageFormatKey.pattern) : messageFormatKey.pattern != null) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            result = (pattern != null ? pattern.hashCode() : 0);
            result = 29 * result + (locale != null ? locale.hashCode() : 0);
            return result;
        }
    }

    static class GetDefaultMessageReturnArg {
        String message;
        boolean foundInBundle;

        public GetDefaultMessageReturnArg(String message, boolean foundInBundle) {
            this.message = message;
            this.foundInBundle = foundInBundle;
        }
    }

    private static class EmptyResourceBundle extends ResourceBundle {
        @Override
        public Enumeration<String> getKeys() {
            return null; // dummy
        }

        @Override
        protected Object handleGetObject(String key) {
            return null; // dummy
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        setReloadBundles(true);
        addDefaultResourceBundle("jdbc");
        setCharsetOfBundles(Charset.forName("gbk"));
        String s = findText(LocalizedTextUtil.class, "jdbc.driverClassName", Locale.getDefault());
        System.out.println(s);
    }
}
