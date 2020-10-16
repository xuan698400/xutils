;!function(e,t){"use strict";"object"==typeof module&&"object"==typeof module.exports?module.exports=e.document?t(e,!0):function(e){if(!e.document)throw new Error("jQuery requires a window with a document");
return t(e)}:t(e)}("undefined"!=typeof window?window:this,function(e,t){"use strict";function n(e,t){t=t||nt;var n=t.createElement("script");
n.text=e,t.head.appendChild(n).parentNode.removeChild(n)}function r(e){var t=!!e&&"length"in e&&e.length,n=gt.type(e);return"function"===n||gt.isWindow(e)?!1:"array"===n||0===t||"number"==typeof t&&t>0&&t-1 in e
}function i(e,t){return e.nodeName&&e.nodeName.toLowerCase()===t.toLowerCase()}function o(e,t,n){return gt.isFunction(t)?gt.grep(e,function(e,r){return!!t.call(e,r,e)!==n
}):t.nodeType?gt.grep(e,function(e){return e===t!==n}):"string"!=typeof t?gt.grep(e,function(e){return st.call(t,e)>-1!==n
}):kt.test(t)?gt.filter(t,e,n):(t=gt.filter(t,e),gt.grep(e,function(e){return st.call(t,e)>-1!==n&&1===e.nodeType}))}function a(e,t){for(;(e=e[t])&&1!==e.nodeType;);return e
}function s(e){var t={};return gt.each(e.match(qt)||[],function(e,n){t[n]=!0}),t}function u(e){return e}function l(e){throw e
}function c(e,t,n,r){var i;try{e&&gt.isFunction(i=e.promise)?i.call(e).done(t).fail(n):e&&gt.isFunction(i=e.then)?i.call(e,t,n):t.apply(void 0,[e].slice(r))
}catch(e){n.apply(void 0,[e])}}function f(){nt.removeEventListener("DOMContentLoaded",f),e.removeEventListener("load",f),gt.ready()
}function p(){this.expando=gt.expando+p.uid++}function d(e){return"true"===e?!0:"false"===e?!1:"null"===e?null:e===+e+""?+e:Mt.test(e)?JSON.parse(e):e
}function h(e,t,n){var r;if(void 0===n&&1===e.nodeType)if(r="data-"+t.replace(It,"-$&").toLowerCase(),n=e.getAttribute(r),"string"==typeof n){try{n=d(n)
}catch(i){}Rt.set(e,t,n)}else n=void 0;return n}function g(e,t,n,r){var i,o=1,a=20,s=r?function(){return r.cur()}:function(){return gt.css(e,t,"")
},u=s(),l=n&&n[3]||(gt.cssNumber[t]?"":"px"),c=(gt.cssNumber[t]||"px"!==l&&+u)&&$t.exec(gt.css(e,t));if(c&&c[3]!==l){l=l||c[3],n=n||[],c=+u||1;
do o=o||".5",c/=o,gt.style(e,t,c+l);while(o!==(o=s()/u)&&1!==o&&--a)}return n&&(c=+c||+u||0,i=n[1]?c+(n[1]+1)*n[2]:+n[2],r&&(r.unit=l,r.start=c,r.end=i)),i
}function m(e){var t,n=e.ownerDocument,r=e.nodeName,i=Xt[r];return i?i:(t=n.body.appendChild(n.createElement(r)),i=gt.css(t,"display"),t.parentNode.removeChild(t),"none"===i&&(i="block"),Xt[r]=i,i)
}function v(e,t){for(var n,r,i=[],o=0,a=e.length;a>o;o++)r=e[o],r.style&&(n=r.style.display,t?("none"===n&&(i[o]=Pt.get(r,"display")||null,i[o]||(r.style.display="")),""===r.style.display&&_t(r)&&(i[o]=m(r))):"none"!==n&&(i[o]="none",Pt.set(r,"display",n)));
for(o=0;a>o;o++)null!=i[o]&&(e[o].style.display=i[o]);return e}function y(e,t){var n;return n="undefined"!=typeof e.getElementsByTagName?e.getElementsByTagName(t||"*"):"undefined"!=typeof e.querySelectorAll?e.querySelectorAll(t||"*"):[],void 0===t||t&&i(e,t)?gt.merge([e],n):n
}function x(e,t){for(var n=0,r=e.length;r>n;n++)Pt.set(e[n],"globalEval",!t||Pt.get(t[n],"globalEval"))}function b(e,t,n,r,i){for(var o,a,s,u,l,c,f=t.createDocumentFragment(),p=[],d=0,h=e.length;h>d;d++)if(o=e[d],o||0===o)if("object"===gt.type(o))gt.merge(p,o.nodeType?[o]:o);
else if(Qt.test(o)){for(a=a||f.appendChild(t.createElement("div")),s=(Vt.exec(o)||["",""])[1].toLowerCase(),u=Yt[s]||Yt._default,a.innerHTML=u[1]+gt.htmlPrefilter(o)+u[2],c=u[0];c--;)a=a.lastChild;
gt.merge(p,a.childNodes),a=f.firstChild,a.textContent=""}else p.push(t.createTextNode(o));for(f.textContent="",d=0;o=p[d++];)if(r&&gt.inArray(o,r)>-1)i&&i.push(o);
else if(l=gt.contains(o.ownerDocument,o),a=y(f.appendChild(o),"script"),l&&x(a),n)for(c=0;o=a[c++];)Gt.test(o.type||"")&&n.push(o);
return f}function w(){return!0}function T(){return!1}function C(){try{return nt.activeElement}catch(e){}}function E(e,t,n,r,i,o){var a,s;
if("object"==typeof t){"string"!=typeof n&&(r=r||n,n=void 0);for(s in t)E(e,s,n,r,t[s],o);return e}if(null==r&&null==i?(i=n,r=n=void 0):null==i&&("string"==typeof n?(i=r,r=void 0):(i=r,r=n,n=void 0)),i===!1)i=T;
else if(!i)return e;return 1===o&&(a=i,i=function(e){return gt().off(e),a.apply(this,arguments)},i.guid=a.guid||(a.guid=gt.guid++)),e.each(function(){gt.event.add(this,t,i,r,n)
})}function k(e,t){return i(e,"table")&&i(11!==t.nodeType?t:t.firstChild,"tr")?gt(">tbody",e)[0]||e:e}function S(e){return e.type=(null!==e.getAttribute("type"))+"/"+e.type,e
}function N(e){var t=on.exec(e.type);return t?e.type=t[1]:e.removeAttribute("type"),e}function D(e,t){var n,r,i,o,a,s,u,l;
if(1===t.nodeType){if(Pt.hasData(e)&&(o=Pt.access(e),a=Pt.set(t,o),l=o.events)){delete a.handle,a.events={};for(i in l)for(n=0,r=l[i].length;r>n;n++)gt.event.add(t,i,l[i][n])
}Rt.hasData(e)&&(s=Rt.access(e),u=gt.extend({},s),Rt.set(t,u))}}function j(e,t){var n=t.nodeName.toLowerCase();"input"===n&&Ut.test(e.type)?t.checked=e.checked:("input"===n||"textarea"===n)&&(t.defaultValue=e.defaultValue)
}function A(e,t,r,i){t=ot.apply([],t);var o,a,s,u,l,c,f=0,p=e.length,d=p-1,h=t[0],g=gt.isFunction(h);if(g||p>1&&"string"==typeof h&&!dt.checkClone&&rn.test(h))return e.each(function(n){var o=e.eq(n);
g&&(t[0]=h.call(this,n,o.html())),A(o,t,r,i)});if(p&&(o=b(t,e[0].ownerDocument,!1,e,i),a=o.firstChild,1===o.childNodes.length&&(o=a),a||i)){for(s=gt.map(y(o,"script"),S),u=s.length;p>f;f++)l=o,f!==d&&(l=gt.clone(l,!0,!0),u&&gt.merge(s,y(l,"script"))),r.call(e[f],l,f);
if(u)for(c=s[s.length-1].ownerDocument,gt.map(s,N),f=0;u>f;f++)l=s[f],Gt.test(l.type||"")&&!Pt.access(l,"globalEval")&&gt.contains(c,l)&&(l.src?gt._evalUrl&&gt._evalUrl(l.src):n(l.textContent.replace(an,""),c))
}return e}function q(e,t,n){for(var r,i=t?gt.filter(t,e):e,o=0;null!=(r=i[o]);o++)n||1!==r.nodeType||gt.cleanData(y(r)),r.parentNode&&(n&&gt.contains(r.ownerDocument,r)&&x(y(r,"script")),r.parentNode.removeChild(r));
return e}function L(e,t,n){var r,i,o,a,s=e.style;return n=n||ln(e),n&&(a=n.getPropertyValue(t)||n[t],""!==a||gt.contains(e.ownerDocument,e)||(a=gt.style(e,t)),!dt.pixelMarginRight()&&un.test(a)&&sn.test(t)&&(r=s.width,i=s.minWidth,o=s.maxWidth,s.minWidth=s.maxWidth=s.width=a,a=n.width,s.width=r,s.minWidth=i,s.maxWidth=o)),void 0!==a?a+"":a
}function H(e,t){return{get:function(){return e()?void delete this.get:(this.get=t).apply(this,arguments)}}}function F(e){if(e in gn)return e;
for(var t=e[0].toUpperCase()+e.slice(1),n=hn.length;n--;)if(e=hn[n]+t,e in gn)return e}function O(e){var t=gt.cssProps[e];
return t||(t=gt.cssProps[e]=F(e)||e),t}function P(e,t,n){var r=$t.exec(t);return r?Math.max(0,r[2]-(n||0))+(r[3]||"px"):t
}function R(e,t,n,r,i){var o,a=0;for(o=n===(r?"border":"content")?4:"width"===t?1:0;4>o;o+=2)"margin"===n&&(a+=gt.css(e,n+Bt[o],!0,i)),r?("content"===n&&(a-=gt.css(e,"padding"+Bt[o],!0,i)),"margin"!==n&&(a-=gt.css(e,"border"+Bt[o]+"Width",!0,i))):(a+=gt.css(e,"padding"+Bt[o],!0,i),"padding"!==n&&(a+=gt.css(e,"border"+Bt[o]+"Width",!0,i)));
return a}function M(e,t,n){var r,i=ln(e),o=L(e,t,i),a="border-box"===gt.css(e,"boxSizing",!1,i);return un.test(o)?o:(r=a&&(dt.boxSizingReliable()||o===e.style[t]),"auto"===o&&(o=e["offset"+t[0].toUpperCase()+t.slice(1)]),o=parseFloat(o)||0,o+R(e,t,n||(a?"border":"content"),r,i)+"px")
}function I(e,t,n,r,i){return new I.prototype.init(e,t,n,r,i)}function W(){vn&&(nt.hidden===!1&&e.requestAnimationFrame?e.requestAnimationFrame(W):e.setTimeout(W,gt.fx.interval),gt.fx.tick())
}function $(){return e.setTimeout(function(){mn=void 0}),mn=gt.now()}function B(e,t){var n,r=0,i={height:e};for(t=t?1:0;4>r;r+=2-t)n=Bt[r],i["margin"+n]=i["padding"+n]=e;
return t&&(i.opacity=i.width=e),i}function _(e,t,n){for(var r,i=(U.tweeners[t]||[]).concat(U.tweeners["*"]),o=0,a=i.length;a>o;o++)if(r=i[o].call(n,t,e))return r
}function z(e,t,n){var r,i,o,a,s,u,l,c,f="width"in t||"height"in t,p=this,d={},h=e.style,g=e.nodeType&&_t(e),m=Pt.get(e,"fxshow");
n.queue||(a=gt._queueHooks(e,"fx"),null==a.unqueued&&(a.unqueued=0,s=a.empty.fire,a.empty.fire=function(){a.unqueued||s()
}),a.unqueued++,p.always(function(){p.always(function(){a.unqueued--,gt.queue(e,"fx").length||a.empty.fire()})}));for(r in t)if(i=t[r],yn.test(i)){if(delete t[r],o=o||"toggle"===i,i===(g?"hide":"show")){if("show"!==i||!m||void 0===m[r])continue;
g=!0}d[r]=m&&m[r]||gt.style(e,r)}if(u=!gt.isEmptyObject(t),u||!gt.isEmptyObject(d)){f&&1===e.nodeType&&(n.overflow=[h.overflow,h.overflowX,h.overflowY],l=m&&m.display,null==l&&(l=Pt.get(e,"display")),c=gt.css(e,"display"),"none"===c&&(l?c=l:(v([e],!0),l=e.style.display||l,c=gt.css(e,"display"),v([e]))),("inline"===c||"inline-block"===c&&null!=l)&&"none"===gt.css(e,"float")&&(u||(p.done(function(){h.display=l
}),null==l&&(c=h.display,l="none"===c?"":c)),h.display="inline-block")),n.overflow&&(h.overflow="hidden",p.always(function(){h.overflow=n.overflow[0],h.overflowX=n.overflow[1],h.overflowY=n.overflow[2]
})),u=!1;for(r in d)u||(m?"hidden"in m&&(g=m.hidden):m=Pt.access(e,"fxshow",{display:l}),o&&(m.hidden=!g),g&&v([e],!0),p.done(function(){g||v([e]),Pt.remove(e,"fxshow");
for(r in d)gt.style(e,r,d[r])})),u=_(g?m[r]:0,r,p),r in m||(m[r]=u.start,g&&(u.end=u.start,u.start=0))}}function X(e,t){var n,r,i,o,a;
for(n in e)if(r=gt.camelCase(n),i=t[r],o=e[n],Array.isArray(o)&&(i=o[1],o=e[n]=o[0]),n!==r&&(e[r]=o,delete e[n]),a=gt.cssHooks[r],a&&"expand"in a){o=a.expand(o),delete e[r];
for(n in o)n in e||(e[n]=o[n],t[n]=i)}else t[r]=i}function U(e,t,n){var r,i,o=0,a=U.prefilters.length,s=gt.Deferred().always(function(){delete u.elem
}),u=function(){if(i)return!1;for(var t=mn||$(),n=Math.max(0,l.startTime+l.duration-t),r=n/l.duration||0,o=1-r,a=0,u=l.tweens.length;u>a;a++)l.tweens[a].run(o);
return s.notifyWith(e,[l,o,n]),1>o&&u?n:(u||s.notifyWith(e,[l,1,0]),s.resolveWith(e,[l]),!1)},l=s.promise({elem:e,props:gt.extend({},t),opts:gt.extend(!0,{specialEasing:{},easing:gt.easing._default},n),originalProperties:t,originalOptions:n,startTime:mn||$(),duration:n.duration,tweens:[],createTween:function(t,n){var r=gt.Tween(e,l.opts,t,n,l.opts.specialEasing[t]||l.opts.easing);
return l.tweens.push(r),r},stop:function(t){var n=0,r=t?l.tweens.length:0;if(i)return this;for(i=!0;r>n;n++)l.tweens[n].run(1);
return t?(s.notifyWith(e,[l,1,0]),s.resolveWith(e,[l,t])):s.rejectWith(e,[l,t]),this}}),c=l.props;for(X(c,l.opts.specialEasing);a>o;o++)if(r=U.prefilters[o].call(l,e,c,l.opts))return gt.isFunction(r.stop)&&(gt._queueHooks(l.elem,l.opts.queue).stop=gt.proxy(r.stop,r)),r;
return gt.map(c,_,l),gt.isFunction(l.opts.start)&&l.opts.start.call(e,l),l.progress(l.opts.progress).done(l.opts.done,l.opts.complete).fail(l.opts.fail).always(l.opts.always),gt.fx.timer(gt.extend(u,{elem:e,anim:l,queue:l.opts.queue})),l
}function V(e){var t=e.match(qt)||[];return t.join(" ")}function G(e){return e.getAttribute&&e.getAttribute("class")||""}function Y(e,t,n,r){var i;
if(Array.isArray(t))gt.each(t,function(t,i){n||jn.test(e)?r(e,i):Y(e+"["+("object"==typeof i&&null!=i?t:"")+"]",i,n,r)});
else if(n||"object"!==gt.type(t))r(e,t);else for(i in t)Y(e+"["+i+"]",t[i],n,r)}function Q(e){return function(t,n){"string"!=typeof t&&(n=t,t="*");
var r,i=0,o=t.toLowerCase().match(qt)||[];if(gt.isFunction(n))for(;r=o[i++];)"+"===r[0]?(r=r.slice(1)||"*",(e[r]=e[r]||[]).unshift(n)):(e[r]=e[r]||[]).push(n)
}}function J(e,t,n,r){function i(s){var u;return o[s]=!0,gt.each(e[s]||[],function(e,s){var l=s(t,n,r);return"string"!=typeof l||a||o[l]?a?!(u=l):void 0:(t.dataTypes.unshift(l),i(l),!1)
}),u}var o={},a=e===$n;return i(t.dataTypes[0])||!o["*"]&&i("*")}function K(e,t){var n,r,i=gt.ajaxSettings.flatOptions||{};
for(n in t)void 0!==t[n]&&((i[n]?e:r||(r={}))[n]=t[n]);return r&&gt.extend(!0,e,r),e}function Z(e,t,n){for(var r,i,o,a,s=e.contents,u=e.dataTypes;"*"===u[0];)u.shift(),void 0===r&&(r=e.mimeType||t.getResponseHeader("Content-Type"));
if(r)for(i in s)if(s[i]&&s[i].test(r)){u.unshift(i);break}if(u[0]in n)o=u[0];else{for(i in n){if(!u[0]||e.converters[i+" "+u[0]]){o=i;
break}a||(a=i)}o=o||a}return o?(o!==u[0]&&u.unshift(o),n[o]):void 0}function et(e,t,n,r){var i,o,a,s,u,l={},c=e.dataTypes.slice();
if(c[1])for(a in e.converters)l[a.toLowerCase()]=e.converters[a];for(o=c.shift();o;)if(e.responseFields[o]&&(n[e.responseFields[o]]=t),!u&&r&&e.dataFilter&&(t=e.dataFilter(t,e.dataType)),u=o,o=c.shift())if("*"===o)o=u;
else if("*"!==u&&u!==o){if(a=l[u+" "+o]||l["* "+o],!a)for(i in l)if(s=i.split(" "),s[1]===o&&(a=l[u+" "+s[0]]||l["* "+s[0]])){a===!0?a=l[i]:l[i]!==!0&&(o=s[0],c.unshift(s[1]));
break}if(a!==!0)if(a&&e.throws)t=a(t);else try{t=a(t)}catch(f){return{state:"parsererror",error:a?f:"No conversion from "+u+" to "+o}
}}return{state:"success",data:t}}var tt=[],nt=e.document,rt=Object.getPrototypeOf,it=tt.slice,ot=tt.concat,at=tt.push,st=tt.indexOf,ut={},lt=ut.toString,ct=ut.hasOwnProperty,ft=ct.toString,pt=ft.call(Object),dt={},ht="3.2.1",gt=function(e,t){return new gt.fn.init(e,t)
},mt=/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g,vt=/^-ms-/,yt=/-([a-z])/g,xt=function(e,t){return t.toUpperCase()};gt.fn=gt.prototype={jquery:ht,constructor:gt,length:0,toArray:function(){return it.call(this)
},get:function(e){return null==e?it.call(this):0>e?this[e+this.length]:this[e]},pushStack:function(e){var t=gt.merge(this.constructor(),e);
return t.prevObject=this,t},each:function(e){return gt.each(this,e)},map:function(e){return this.pushStack(gt.map(this,function(t,n){return e.call(t,n,t)
}))},slice:function(){return this.pushStack(it.apply(this,arguments))},first:function(){return this.eq(0)},last:function(){return this.eq(-1)
},eq:function(e){var t=this.length,n=+e+(0>e?t:0);return this.pushStack(n>=0&&t>n?[this[n]]:[])},end:function(){return this.prevObject||this.constructor()
},push:at,sort:tt.sort,splice:tt.splice},gt.extend=gt.fn.extend=function(){var e,t,n,r,i,o,a=arguments[0]||{},s=1,u=arguments.length,l=!1;
for("boolean"==typeof a&&(l=a,a=arguments[s]||{},s++),"object"==typeof a||gt.isFunction(a)||(a={}),s===u&&(a=this,s--);u>s;s++)if(null!=(e=arguments[s]))for(t in e)n=a[t],r=e[t],a!==r&&(l&&r&&(gt.isPlainObject(r)||(i=Array.isArray(r)))?(i?(i=!1,o=n&&Array.isArray(n)?n:[]):o=n&&gt.isPlainObject(n)?n:{},a[t]=gt.extend(l,o,r)):void 0!==r&&(a[t]=r));
return a},gt.extend({expando:"jQuery"+(ht+Math.random()).replace(/\D/g,""),isReady:!0,error:function(e){throw new Error(e)
},noop:function(){},isFunction:function(e){return"function"===gt.type(e)},isWindow:function(e){return null!=e&&e===e.window
},isNumeric:function(e){var t=gt.type(e);return("number"===t||"string"===t)&&!isNaN(e-parseFloat(e))},isPlainObject:function(e){var t,n;
return e&&"[object Object]"===lt.call(e)?(t=rt(e))?(n=ct.call(t,"constructor")&&t.constructor,"function"==typeof n&&ft.call(n)===pt):!0:!1
},isEmptyObject:function(e){var t;for(t in e)return!1;return!0},type:function(e){return null==e?e+"":"object"==typeof e||"function"==typeof e?ut[lt.call(e)]||"object":typeof e
},globalEval:function(e){n(e)},camelCase:function(e){return e.replace(vt,"ms-").replace(yt,xt)},each:function(e,t){var n,i=0;
if(r(e))for(n=e.length;n>i&&t.call(e[i],i,e[i])!==!1;i++);else for(i in e)if(t.call(e[i],i,e[i])===!1)break;return e},trim:function(e){return null==e?"":(e+"").replace(mt,"")
},makeArray:function(e,t){var n=t||[];return null!=e&&(r(Object(e))?gt.merge(n,"string"==typeof e?[e]:e):at.call(n,e)),n},inArray:function(e,t,n){return null==t?-1:st.call(t,e,n)
},merge:function(e,t){for(var n=+t.length,r=0,i=e.length;n>r;r++)e[i++]=t[r];return e.length=i,e},grep:function(e,t,n){for(var r,i=[],o=0,a=e.length,s=!n;a>o;o++)r=!t(e[o],o),r!==s&&i.push(e[o]);
return i},map:function(e,t,n){var i,o,a=0,s=[];if(r(e))for(i=e.length;i>a;a++)o=t(e[a],a,n),null!=o&&s.push(o);else for(a in e)o=t(e[a],a,n),null!=o&&s.push(o);
return ot.apply([],s)},guid:1,proxy:function(e,t){var n,r,i;return"string"==typeof t&&(n=e[t],t=e,e=n),gt.isFunction(e)?(r=it.call(arguments,2),i=function(){return e.apply(t||this,r.concat(it.call(arguments)))
},i.guid=e.guid=e.guid||gt.guid++,i):void 0},now:Date.now,support:dt}),"function"==typeof Symbol&&(gt.fn[Symbol.iterator]=tt[Symbol.iterator]),gt.each("Boolean Number String Function Array Date RegExp Object Error Symbol".split(" "),function(e,t){ut["[object "+t+"]"]=t.toLowerCase()
});var bt=function(e){function t(e,t,n,r){var i,o,a,s,u,l,c,p=t&&t.ownerDocument,h=t?t.nodeType:9;if(n=n||[],"string"!=typeof e||!e||1!==h&&9!==h&&11!==h)return n;
if(!r&&((t?t.ownerDocument||t:$)!==H&&L(t),t=t||H,O)){if(11!==h&&(u=vt.exec(e)))if(i=u[1]){if(9===h){if(!(a=t.getElementById(i)))return n;
if(a.id===i)return n.push(a),n}else if(p&&(a=p.getElementById(i))&&I(t,a)&&a.id===i)return n.push(a),n}else{if(u[2])return K.apply(n,t.getElementsByTagName(e)),n;
if((i=u[3])&&T.getElementsByClassName&&t.getElementsByClassName)return K.apply(n,t.getElementsByClassName(i)),n}if(!(!T.qsa||U[e+" "]||P&&P.test(e))){if(1!==h)p=t,c=e;
else if("object"!==t.nodeName.toLowerCase()){for((s=t.getAttribute("id"))?s=s.replace(wt,Tt):t.setAttribute("id",s=W),l=S(e),o=l.length;o--;)l[o]="#"+s+" "+d(l[o]);
c=l.join(","),p=yt.test(e)&&f(t.parentNode)||t}if(c)try{return K.apply(n,p.querySelectorAll(c)),n}catch(g){}finally{s===W&&t.removeAttribute("id")
}}}return D(e.replace(st,"$1"),t,n,r)}function n(){function e(n,r){return t.push(n+" ")>C.cacheLength&&delete e[t.shift()],e[n+" "]=r
}var t=[];return e}function r(e){return e[W]=!0,e}function i(e){var t=H.createElement("fieldset");try{return!!e(t)}catch(n){return!1
}finally{t.parentNode&&t.parentNode.removeChild(t),t=null}}function o(e,t){for(var n=e.split("|"),r=n.length;r--;)C.attrHandle[n[r]]=t
}function a(e,t){var n=t&&e,r=n&&1===e.nodeType&&1===t.nodeType&&e.sourceIndex-t.sourceIndex;if(r)return r;if(n)for(;n=n.nextSibling;)if(n===t)return-1;
return e?1:-1}function s(e){return function(t){var n=t.nodeName.toLowerCase();return"input"===n&&t.type===e}}function u(e){return function(t){var n=t.nodeName.toLowerCase();
return("input"===n||"button"===n)&&t.type===e}}function l(e){return function(t){return"form"in t?t.parentNode&&t.disabled===!1?"label"in t?"label"in t.parentNode?t.parentNode.disabled===e:t.disabled===e:t.isDisabled===e||t.isDisabled!==!e&&Et(t)===e:t.disabled===e:"label"in t?t.disabled===e:!1
}}function c(e){return r(function(t){return t=+t,r(function(n,r){for(var i,o=e([],n.length,t),a=o.length;a--;)n[i=o[a]]&&(n[i]=!(r[i]=n[i]))
})})}function f(e){return e&&"undefined"!=typeof e.getElementsByTagName&&e}function p(){}function d(e){for(var t=0,n=e.length,r="";n>t;t++)r+=e[t].value;
return r}function h(e,t,n){var r=t.dir,i=t.next,o=i||r,a=n&&"parentNode"===o,s=_++;return t.first?function(t,n,i){for(;t=t[r];)if(1===t.nodeType||a)return e(t,n,i);
return!1}:function(t,n,u){var l,c,f,p=[B,s];if(u){for(;t=t[r];)if((1===t.nodeType||a)&&e(t,n,u))return!0}else for(;t=t[r];)if(1===t.nodeType||a)if(f=t[W]||(t[W]={}),c=f[t.uniqueID]||(f[t.uniqueID]={}),i&&i===t.nodeName.toLowerCase())t=t[r]||t;
else{if((l=c[o])&&l[0]===B&&l[1]===s)return p[2]=l[2];if(c[o]=p,p[2]=e(t,n,u))return!0}return!1}}function g(e){return e.length>1?function(t,n,r){for(var i=e.length;i--;)if(!e[i](t,n,r))return!1;
return!0}:e[0]}function m(e,n,r){for(var i=0,o=n.length;o>i;i++)t(e,n[i],r);return r}function v(e,t,n,r,i){for(var o,a=[],s=0,u=e.length,l=null!=t;u>s;s++)(o=e[s])&&(!n||n(o,r,i))&&(a.push(o),l&&t.push(s));
return a}function y(e,t,n,i,o,a){return i&&!i[W]&&(i=y(i)),o&&!o[W]&&(o=y(o,a)),r(function(r,a,s,u){var l,c,f,p=[],d=[],h=a.length,g=r||m(t||"*",s.nodeType?[s]:s,[]),y=!e||!r&&t?g:v(g,p,e,s,u),x=n?o||(r?e:h||i)?[]:a:y;
if(n&&n(y,x,s,u),i)for(l=v(x,d),i(l,[],s,u),c=l.length;c--;)(f=l[c])&&(x[d[c]]=!(y[d[c]]=f));if(r){if(o||e){if(o){for(l=[],c=x.length;c--;)(f=x[c])&&l.push(y[c]=f);
o(null,x=[],l,u)}for(c=x.length;c--;)(f=x[c])&&(l=o?et(r,f):p[c])>-1&&(r[l]=!(a[l]=f))}}else x=v(x===a?x.splice(h,x.length):x),o?o(null,a,x,u):K.apply(a,x)
})}function x(e){for(var t,n,r,i=e.length,o=C.relative[e[0].type],a=o||C.relative[" "],s=o?1:0,u=h(function(e){return e===t
},a,!0),l=h(function(e){return et(t,e)>-1},a,!0),c=[function(e,n,r){var i=!o&&(r||n!==j)||((t=n).nodeType?u(e,n,r):l(e,n,r));
return t=null,i}];i>s;s++)if(n=C.relative[e[s].type])c=[h(g(c),n)];else{if(n=C.filter[e[s].type].apply(null,e[s].matches),n[W]){for(r=++s;i>r&&!C.relative[e[r].type];r++);return y(s>1&&g(c),s>1&&d(e.slice(0,s-1).concat({value:" "===e[s-2].type?"*":""})).replace(st,"$1"),n,r>s&&x(e.slice(s,r)),i>r&&x(e=e.slice(r)),i>r&&d(e))
}c.push(n)}return g(c)}function b(e,n){var i=n.length>0,o=e.length>0,a=function(r,a,s,u,l){var c,f,p,d=0,h="0",g=r&&[],m=[],y=j,x=r||o&&C.find.TAG("*",l),b=B+=null==y?1:Math.random()||.1,w=x.length;
for(l&&(j=a===H||a||l);h!==w&&null!=(c=x[h]);h++){if(o&&c){for(f=0,a||c.ownerDocument===H||(L(c),s=!O);p=e[f++];)if(p(c,a||H,s)){u.push(c);
break}l&&(B=b)}i&&((c=!p&&c)&&d--,r&&g.push(c))}if(d+=h,i&&h!==d){for(f=0;p=n[f++];)p(g,m,a,s);if(r){if(d>0)for(;h--;)g[h]||m[h]||(m[h]=Q.call(u));
m=v(m)}K.apply(u,m),l&&!r&&m.length>0&&d+n.length>1&&t.uniqueSort(u)}return l&&(B=b,j=y),g};return i?r(a):a}var w,T,C,E,k,S,N,D,j,A,q,L,H,F,O,P,R,M,I,W="sizzle"+1*new Date,$=e.document,B=0,_=0,z=n(),X=n(),U=n(),V=function(e,t){return e===t&&(q=!0),0
},G={}.hasOwnProperty,Y=[],Q=Y.pop,J=Y.push,K=Y.push,Z=Y.slice,et=function(e,t){for(var n=0,r=e.length;r>n;n++)if(e[n]===t)return n;
return-1},tt="checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped",nt="[\\x20\\t\\r\\n\\f]",rt="(?:\\\\.|[\\w-]|[^\x00-\\xa0])+",it="\\["+nt+"*("+rt+")(?:"+nt+"*([*^$|!~]?=)"+nt+"*(?:'((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\"|("+rt+"))|)"+nt+"*\\]",ot=":("+rt+")(?:\\((('((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\")|((?:\\\\.|[^\\\\()[\\]]|"+it+")*)|.*)\\)|)",at=new RegExp(nt+"+","g"),st=new RegExp("^"+nt+"+|((?:^|[^\\\\])(?:\\\\.)*)"+nt+"+$","g"),ut=new RegExp("^"+nt+"*,"+nt+"*"),lt=new RegExp("^"+nt+"*([>+~]|"+nt+")"+nt+"*"),ct=new RegExp("="+nt+"*([^\\]'\"]*?)"+nt+"*\\]","g"),ft=new RegExp(ot),pt=new RegExp("^"+rt+"$"),dt={ID:new RegExp("^#("+rt+")"),CLASS:new RegExp("^\\.("+rt+")"),TAG:new RegExp("^("+rt+"|[*])"),ATTR:new RegExp("^"+it),PSEUDO:new RegExp("^"+ot),CHILD:new RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\("+nt+"*(even|odd|(([+-]|)(\\d*)n|)"+nt+"*(?:([+-]|)"+nt+"*(\\d+)|))"+nt+"*\\)|)","i"),bool:new RegExp("^(?:"+tt+")$","i"),needsContext:new RegExp("^"+nt+"*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\("+nt+"*((?:-\\d)?\\d*)"+nt+"*\\)|)(?=[^-]|$)","i")},ht=/^(?:input|select|textarea|button)$/i,gt=/^h\d$/i,mt=/^[^{]+\{\s*\[native \w/,vt=/^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/,yt=/[+~]/,xt=new RegExp("\\\\([\\da-f]{1,6}"+nt+"?|("+nt+")|.)","ig"),bt=function(e,t,n){var r="0x"+t-65536;
return r!==r||n?t:0>r?String.fromCharCode(r+65536):String.fromCharCode(r>>10|55296,1023&r|56320)},wt=/([\0-\x1f\x7f]|^-?\d)|^-$|[^\0-\x1f\x7f-\uFFFF\w-]/g,Tt=function(e,t){return t?"\x00"===e?"�":e.slice(0,-1)+"\\"+e.charCodeAt(e.length-1).toString(16)+" ":"\\"+e
},Ct=function(){L()},Et=h(function(e){return e.disabled===!0&&("form"in e||"label"in e)},{dir:"parentNode",next:"legend"});
try{K.apply(Y=Z.call($.childNodes),$.childNodes),Y[$.childNodes.length].nodeType}catch(kt){K={apply:Y.length?function(e,t){J.apply(e,Z.call(t))
}:function(e,t){for(var n=e.length,r=0;e[n++]=t[r++];);e.length=n-1}}}T=t.support={},k=t.isXML=function(e){var t=e&&(e.ownerDocument||e).documentElement;
return t?"HTML"!==t.nodeName:!1},L=t.setDocument=function(e){var t,n,r=e?e.ownerDocument||e:$;return r!==H&&9===r.nodeType&&r.documentElement?(H=r,F=H.documentElement,O=!k(H),$!==H&&(n=H.defaultView)&&n.top!==n&&(n.addEventListener?n.addEventListener("unload",Ct,!1):n.attachEvent&&n.attachEvent("onunload",Ct)),T.attributes=i(function(e){return e.className="i",!e.getAttribute("className")
}),T.getElementsByTagName=i(function(e){return e.appendChild(H.createComment("")),!e.getElementsByTagName("*").length}),T.getElementsByClassName=mt.test(H.getElementsByClassName),T.getById=i(function(e){return F.appendChild(e).id=W,!H.getElementsByName||!H.getElementsByName(W).length
}),T.getById?(C.filter.ID=function(e){var t=e.replace(xt,bt);return function(e){return e.getAttribute("id")===t}},C.find.ID=function(e,t){if("undefined"!=typeof t.getElementById&&O){var n=t.getElementById(e);
return n?[n]:[]}}):(C.filter.ID=function(e){var t=e.replace(xt,bt);return function(e){var n="undefined"!=typeof e.getAttributeNode&&e.getAttributeNode("id");
return n&&n.value===t}},C.find.ID=function(e,t){if("undefined"!=typeof t.getElementById&&O){var n,r,i,o=t.getElementById(e);
if(o){if(n=o.getAttributeNode("id"),n&&n.value===e)return[o];for(i=t.getElementsByName(e),r=0;o=i[r++];)if(n=o.getAttributeNode("id"),n&&n.value===e)return[o]
}return[]}}),C.find.TAG=T.getElementsByTagName?function(e,t){return"undefined"!=typeof t.getElementsByTagName?t.getElementsByTagName(e):T.qsa?t.querySelectorAll(e):void 0
}:function(e,t){var n,r=[],i=0,o=t.getElementsByTagName(e);if("*"===e){for(;n=o[i++];)1===n.nodeType&&r.push(n);return r}return o
},C.find.CLASS=T.getElementsByClassName&&function(e,t){return"undefined"!=typeof t.getElementsByClassName&&O?t.getElementsByClassName(e):void 0
},R=[],P=[],(T.qsa=mt.test(H.querySelectorAll))&&(i(function(e){F.appendChild(e).innerHTML="<a id='"+W+"'></a><select id='"+W+"-\r\\' msallowcapture=''><option selected=''></option></select>",e.querySelectorAll("[msallowcapture^='']").length&&P.push("[*^$]="+nt+"*(?:''|\"\")"),e.querySelectorAll("[selected]").length||P.push("\\["+nt+"*(?:value|"+tt+")"),e.querySelectorAll("[id~="+W+"-]").length||P.push("~="),e.querySelectorAll(":checked").length||P.push(":checked"),e.querySelectorAll("a#"+W+"+*").length||P.push(".#.+[+~]")
}),i(function(e){e.innerHTML="<a href='' disabled='disabled'></a><select disabled='disabled'><option/></select>";var t=H.createElement("input");
t.setAttribute("type","hidden"),e.appendChild(t).setAttribute("name","D"),e.querySelectorAll("[name=d]").length&&P.push("name"+nt+"*[*^$|!~]?="),2!==e.querySelectorAll(":enabled").length&&P.push(":enabled",":disabled"),F.appendChild(e).disabled=!0,2!==e.querySelectorAll(":disabled").length&&P.push(":enabled",":disabled"),e.querySelectorAll("*,:x"),P.push(",.*:")
})),(T.matchesSelector=mt.test(M=F.matches||F.webkitMatchesSelector||F.mozMatchesSelector||F.oMatchesSelector||F.msMatchesSelector))&&i(function(e){T.disconnectedMatch=M.call(e,"*"),M.call(e,"[s!='']:x"),R.push("!=",ot)
}),P=P.length&&new RegExp(P.join("|")),R=R.length&&new RegExp(R.join("|")),t=mt.test(F.compareDocumentPosition),I=t||mt.test(F.contains)?function(e,t){var n=9===e.nodeType?e.documentElement:e,r=t&&t.parentNode;
return e===r||!(!r||1!==r.nodeType||!(n.contains?n.contains(r):e.compareDocumentPosition&&16&e.compareDocumentPosition(r)))
}:function(e,t){if(t)for(;t=t.parentNode;)if(t===e)return!0;return!1},V=t?function(e,t){if(e===t)return q=!0,0;var n=!e.compareDocumentPosition-!t.compareDocumentPosition;
return n?n:(n=(e.ownerDocument||e)===(t.ownerDocument||t)?e.compareDocumentPosition(t):1,1&n||!T.sortDetached&&t.compareDocumentPosition(e)===n?e===H||e.ownerDocument===$&&I($,e)?-1:t===H||t.ownerDocument===$&&I($,t)?1:A?et(A,e)-et(A,t):0:4&n?-1:1)
}:function(e,t){if(e===t)return q=!0,0;var n,r=0,i=e.parentNode,o=t.parentNode,s=[e],u=[t];if(!i||!o)return e===H?-1:t===H?1:i?-1:o?1:A?et(A,e)-et(A,t):0;
if(i===o)return a(e,t);for(n=e;n=n.parentNode;)s.unshift(n);for(n=t;n=n.parentNode;)u.unshift(n);for(;s[r]===u[r];)r++;return r?a(s[r],u[r]):s[r]===$?-1:u[r]===$?1:0
},H):H},t.matches=function(e,n){return t(e,null,null,n)},t.matchesSelector=function(e,n){if((e.ownerDocument||e)!==H&&L(e),n=n.replace(ct,"='$1']"),!(!T.matchesSelector||!O||U[n+" "]||R&&R.test(n)||P&&P.test(n)))try{var r=M.call(e,n);
if(r||T.disconnectedMatch||e.document&&11!==e.document.nodeType)return r}catch(i){}return t(n,H,null,[e]).length>0},t.contains=function(e,t){return(e.ownerDocument||e)!==H&&L(e),I(e,t)
},t.attr=function(e,t){(e.ownerDocument||e)!==H&&L(e);var n=C.attrHandle[t.toLowerCase()],r=n&&G.call(C.attrHandle,t.toLowerCase())?n(e,t,!O):void 0;
return void 0!==r?r:T.attributes||!O?e.getAttribute(t):(r=e.getAttributeNode(t))&&r.specified?r.value:null},t.escape=function(e){return(e+"").replace(wt,Tt)
},t.error=function(e){throw new Error("Syntax error, unrecognized expression: "+e)},t.uniqueSort=function(e){var t,n=[],r=0,i=0;
if(q=!T.detectDuplicates,A=!T.sortStable&&e.slice(0),e.sort(V),q){for(;t=e[i++];)t===e[i]&&(r=n.push(i));for(;r--;)e.splice(n[r],1)
}return A=null,e},E=t.getText=function(e){var t,n="",r=0,i=e.nodeType;if(i){if(1===i||9===i||11===i){if("string"==typeof e.textContent)return e.textContent;
for(e=e.firstChild;e;e=e.nextSibling)n+=E(e)}else if(3===i||4===i)return e.nodeValue}else for(;t=e[r++];)n+=E(t);return n
},C=t.selectors={cacheLength:50,createPseudo:r,match:dt,attrHandle:{},find:{},relative:{">":{dir:"parentNode",first:!0}," ":{dir:"parentNode"},"+":{dir:"previousSibling",first:!0},"~":{dir:"previousSibling"}},preFilter:{ATTR:function(e){return e[1]=e[1].replace(xt,bt),e[3]=(e[3]||e[4]||e[5]||"").replace(xt,bt),"~="===e[2]&&(e[3]=" "+e[3]+" "),e.slice(0,4)
},CHILD:function(e){return e[1]=e[1].toLowerCase(),"nth"===e[1].slice(0,3)?(e[3]||t.error(e[0]),e[4]=+(e[4]?e[5]+(e[6]||1):2*("even"===e[3]||"odd"===e[3])),e[5]=+(e[7]+e[8]||"odd"===e[3])):e[3]&&t.error(e[0]),e
},PSEUDO:function(e){var t,n=!e[6]&&e[2];return dt.CHILD.test(e[0])?null:(e[3]?e[2]=e[4]||e[5]||"":n&&ft.test(n)&&(t=S(n,!0))&&(t=n.indexOf(")",n.length-t)-n.length)&&(e[0]=e[0].slice(0,t),e[2]=n.slice(0,t)),e.slice(0,3))
}},filter:{TAG:function(e){var t=e.replace(xt,bt).toLowerCase();return"*"===e?function(){return!0}:function(e){return e.nodeName&&e.nodeName.toLowerCase()===t
}},CLASS:function(e){var t=z[e+" "];return t||(t=new RegExp("(^|"+nt+")"+e+"("+nt+"|$)"))&&z(e,function(e){return t.test("string"==typeof e.className&&e.className||"undefined"!=typeof e.getAttribute&&e.getAttribute("class")||"")
})},ATTR:function(e,n,r){return function(i){var o=t.attr(i,e);return null==o?"!="===n:n?(o+="","="===n?o===r:"!="===n?o!==r:"^="===n?r&&0===o.indexOf(r):"*="===n?r&&o.indexOf(r)>-1:"$="===n?r&&o.slice(-r.length)===r:"~="===n?(" "+o.replace(at," ")+" ").indexOf(r)>-1:"|="===n?o===r||o.slice(0,r.length+1)===r+"-":!1):!0
}},CHILD:function(e,t,n,r,i){var o="nth"!==e.slice(0,3),a="last"!==e.slice(-4),s="of-type"===t;return 1===r&&0===i?function(e){return!!e.parentNode
}:function(t,n,u){var l,c,f,p,d,h,g=o!==a?"nextSibling":"previousSibling",m=t.parentNode,v=s&&t.nodeName.toLowerCase(),y=!u&&!s,x=!1;
if(m){if(o){for(;g;){for(p=t;p=p[g];)if(s?p.nodeName.toLowerCase()===v:1===p.nodeType)return!1;h=g="only"===e&&!h&&"nextSibling"
}return!0}if(h=[a?m.firstChild:m.lastChild],a&&y){for(p=m,f=p[W]||(p[W]={}),c=f[p.uniqueID]||(f[p.uniqueID]={}),l=c[e]||[],d=l[0]===B&&l[1],x=d&&l[2],p=d&&m.childNodes[d];p=++d&&p&&p[g]||(x=d=0)||h.pop();)if(1===p.nodeType&&++x&&p===t){c[e]=[B,d,x];
break}}else if(y&&(p=t,f=p[W]||(p[W]={}),c=f[p.uniqueID]||(f[p.uniqueID]={}),l=c[e]||[],d=l[0]===B&&l[1],x=d),x===!1)for(;(p=++d&&p&&p[g]||(x=d=0)||h.pop())&&((s?p.nodeName.toLowerCase()!==v:1!==p.nodeType)||!++x||(y&&(f=p[W]||(p[W]={}),c=f[p.uniqueID]||(f[p.uniqueID]={}),c[e]=[B,x]),p!==t)););return x-=i,x===r||x%r===0&&x/r>=0
}}},PSEUDO:function(e,n){var i,o=C.pseudos[e]||C.setFilters[e.toLowerCase()]||t.error("unsupported pseudo: "+e);return o[W]?o(n):o.length>1?(i=[e,e,"",n],C.setFilters.hasOwnProperty(e.toLowerCase())?r(function(e,t){for(var r,i=o(e,n),a=i.length;a--;)r=et(e,i[a]),e[r]=!(t[r]=i[a])
}):function(e){return o(e,0,i)}):o}},pseudos:{not:r(function(e){var t=[],n=[],i=N(e.replace(st,"$1"));return i[W]?r(function(e,t,n,r){for(var o,a=i(e,null,r,[]),s=e.length;s--;)(o=a[s])&&(e[s]=!(t[s]=o))
}):function(e,r,o){return t[0]=e,i(t,null,o,n),t[0]=null,!n.pop()}}),has:r(function(e){return function(n){return t(e,n).length>0
}}),contains:r(function(e){return e=e.replace(xt,bt),function(t){return(t.textContent||t.innerText||E(t)).indexOf(e)>-1}}),lang:r(function(e){return pt.test(e||"")||t.error("unsupported lang: "+e),e=e.replace(xt,bt).toLowerCase(),function(t){var n;
do if(n=O?t.lang:t.getAttribute("xml:lang")||t.getAttribute("lang"))return n=n.toLowerCase(),n===e||0===n.indexOf(e+"-");
while((t=t.parentNode)&&1===t.nodeType);return!1}}),target:function(t){var n=e.location&&e.location.hash;return n&&n.slice(1)===t.id
},root:function(e){return e===F},focus:function(e){return e===H.activeElement&&(!H.hasFocus||H.hasFocus())&&!!(e.type||e.href||~e.tabIndex)
},enabled:l(!1),disabled:l(!0),checked:function(e){var t=e.nodeName.toLowerCase();return"input"===t&&!!e.checked||"option"===t&&!!e.selected
},selected:function(e){return e.parentNode&&e.parentNode.selectedIndex,e.selected===!0},empty:function(e){for(e=e.firstChild;e;e=e.nextSibling)if(e.nodeType<6)return!1;
return!0},parent:function(e){return!C.pseudos.empty(e)},header:function(e){return gt.test(e.nodeName)},input:function(e){return ht.test(e.nodeName)
},button:function(e){var t=e.nodeName.toLowerCase();return"input"===t&&"button"===e.type||"button"===t},text:function(e){var t;
return"input"===e.nodeName.toLowerCase()&&"text"===e.type&&(null==(t=e.getAttribute("type"))||"text"===t.toLowerCase())},first:c(function(){return[0]
}),last:c(function(e,t){return[t-1]}),eq:c(function(e,t,n){return[0>n?n+t:n]}),even:c(function(e,t){for(var n=0;t>n;n+=2)e.push(n);
return e}),odd:c(function(e,t){for(var n=1;t>n;n+=2)e.push(n);return e}),lt:c(function(e,t,n){for(var r=0>n?n+t:n;--r>=0;)e.push(r);
return e}),gt:c(function(e,t,n){for(var r=0>n?n+t:n;++r<t;)e.push(r);return e})}},C.pseudos.nth=C.pseudos.eq;for(w in{radio:!0,checkbox:!0,file:!0,password:!0,image:!0})C.pseudos[w]=s(w);
for(w in{submit:!0,reset:!0})C.pseudos[w]=u(w);return p.prototype=C.filters=C.pseudos,C.setFilters=new p,S=t.tokenize=function(e,n){var r,i,o,a,s,u,l,c=X[e+" "];
if(c)return n?0:c.slice(0);for(s=e,u=[],l=C.preFilter;s;){(!r||(i=ut.exec(s)))&&(i&&(s=s.slice(i[0].length)||s),u.push(o=[])),r=!1,(i=lt.exec(s))&&(r=i.shift(),o.push({value:r,type:i[0].replace(st," ")}),s=s.slice(r.length));
for(a in C.filter)!(i=dt[a].exec(s))||l[a]&&!(i=l[a](i))||(r=i.shift(),o.push({value:r,type:a,matches:i}),s=s.slice(r.length));
if(!r)break}return n?s.length:s?t.error(e):X(e,u).slice(0)},N=t.compile=function(e,t){var n,r=[],i=[],o=U[e+" "];if(!o){for(t||(t=S(e)),n=t.length;n--;)o=x(t[n]),o[W]?r.push(o):i.push(o);
o=U(e,b(i,r)),o.selector=e}return o},D=t.select=function(e,t,n,r){var i,o,a,s,u,l="function"==typeof e&&e,c=!r&&S(e=l.selector||e);
if(n=n||[],1===c.length){if(o=c[0]=c[0].slice(0),o.length>2&&"ID"===(a=o[0]).type&&9===t.nodeType&&O&&C.relative[o[1].type]){if(t=(C.find.ID(a.matches[0].replace(xt,bt),t)||[])[0],!t)return n;
l&&(t=t.parentNode),e=e.slice(o.shift().value.length)}for(i=dt.needsContext.test(e)?0:o.length;i--&&(a=o[i],!C.relative[s=a.type]);)if((u=C.find[s])&&(r=u(a.matches[0].replace(xt,bt),yt.test(o[0].type)&&f(t.parentNode)||t))){if(o.splice(i,1),e=r.length&&d(o),!e)return K.apply(n,r),n;
break}}return(l||N(e,c))(r,t,!O,n,!t||yt.test(e)&&f(t.parentNode)||t),n},T.sortStable=W.split("").sort(V).join("")===W,T.detectDuplicates=!!q,L(),T.sortDetached=i(function(e){return 1&e.compareDocumentPosition(H.createElement("fieldset"))
}),i(function(e){return e.innerHTML="<a href='#'></a>","#"===e.firstChild.getAttribute("href")})||o("type|href|height|width",function(e,t,n){return n?void 0:e.getAttribute(t,"type"===t.toLowerCase()?1:2)
}),T.attributes&&i(function(e){return e.innerHTML="<input/>",e.firstChild.setAttribute("value",""),""===e.firstChild.getAttribute("value")
})||o("value",function(e,t,n){return n||"input"!==e.nodeName.toLowerCase()?void 0:e.defaultValue}),i(function(e){return null==e.getAttribute("disabled")
})||o(tt,function(e,t,n){var r;return n?void 0:e[t]===!0?t.toLowerCase():(r=e.getAttributeNode(t))&&r.specified?r.value:null
}),t}(e);gt.find=bt,gt.expr=bt.selectors,gt.expr[":"]=gt.expr.pseudos,gt.uniqueSort=gt.unique=bt.uniqueSort,gt.text=bt.getText,gt.isXMLDoc=bt.isXML,gt.contains=bt.contains,gt.escapeSelector=bt.escape;
var wt=function(e,t,n){for(var r=[],i=void 0!==n;(e=e[t])&&9!==e.nodeType;)if(1===e.nodeType){if(i&&gt(e).is(n))break;r.push(e)
}return r},Tt=function(e,t){for(var n=[];e;e=e.nextSibling)1===e.nodeType&&e!==t&&n.push(e);return n},Ct=gt.expr.match.needsContext,Et=/^<([a-z][^\/\0>:\x20\t\r\n\f]*)[\x20\t\r\n\f]*\/?>(?:<\/\1>|)$/i,kt=/^.[^:#\[\.,]*$/;
gt.filter=function(e,t,n){var r=t[0];return n&&(e=":not("+e+")"),1===t.length&&1===r.nodeType?gt.find.matchesSelector(r,e)?[r]:[]:gt.find.matches(e,gt.grep(t,function(e){return 1===e.nodeType
}))},gt.fn.extend({find:function(e){var t,n,r=this.length,i=this;if("string"!=typeof e)return this.pushStack(gt(e).filter(function(){for(t=0;r>t;t++)if(gt.contains(i[t],this))return!0
}));for(n=this.pushStack([]),t=0;r>t;t++)gt.find(e,i[t],n);return r>1?gt.uniqueSort(n):n},filter:function(e){return this.pushStack(o(this,e||[],!1))
},not:function(e){return this.pushStack(o(this,e||[],!0))},is:function(e){return!!o(this,"string"==typeof e&&Ct.test(e)?gt(e):e||[],!1).length
}});var St,Nt=/^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]+))$/,Dt=gt.fn.init=function(e,t,n){var r,i;if(!e)return this;if(n=n||St,"string"==typeof e){if(r="<"===e[0]&&">"===e[e.length-1]&&e.length>=3?[null,e,null]:Nt.exec(e),!r||!r[1]&&t)return!t||t.jquery?(t||n).find(e):this.constructor(t).find(e);
if(r[1]){if(t=t instanceof gt?t[0]:t,gt.merge(this,gt.parseHTML(r[1],t&&t.nodeType?t.ownerDocument||t:nt,!0)),Et.test(r[1])&&gt.isPlainObject(t))for(r in t)gt.isFunction(this[r])?this[r](t[r]):this.attr(r,t[r]);
return this}return i=nt.getElementById(r[2]),i&&(this[0]=i,this.length=1),this}return e.nodeType?(this[0]=e,this.length=1,this):gt.isFunction(e)?void 0!==n.ready?n.ready(e):e(gt):gt.makeArray(e,this)
};Dt.prototype=gt.fn,St=gt(nt);var jt=/^(?:parents|prev(?:Until|All))/,At={children:!0,contents:!0,next:!0,prev:!0};gt.fn.extend({has:function(e){var t=gt(e,this),n=t.length;
return this.filter(function(){for(var e=0;n>e;e++)if(gt.contains(this,t[e]))return!0})},closest:function(e,t){var n,r=0,i=this.length,o=[],a="string"!=typeof e&&gt(e);
if(!Ct.test(e))for(;i>r;r++)for(n=this[r];n&&n!==t;n=n.parentNode)if(n.nodeType<11&&(a?a.index(n)>-1:1===n.nodeType&&gt.find.matchesSelector(n,e))){o.push(n);
break}return this.pushStack(o.length>1?gt.uniqueSort(o):o)},index:function(e){return e?"string"==typeof e?st.call(gt(e),this[0]):st.call(this,e.jquery?e[0]:e):this[0]&&this[0].parentNode?this.first().prevAll().length:-1
},add:function(e,t){return this.pushStack(gt.uniqueSort(gt.merge(this.get(),gt(e,t))))},addBack:function(e){return this.add(null==e?this.prevObject:this.prevObject.filter(e))
}}),gt.each({parent:function(e){var t=e.parentNode;return t&&11!==t.nodeType?t:null},parents:function(e){return wt(e,"parentNode")
},parentsUntil:function(e,t,n){return wt(e,"parentNode",n)},next:function(e){return a(e,"nextSibling")},prev:function(e){return a(e,"previousSibling")
},nextAll:function(e){return wt(e,"nextSibling")},prevAll:function(e){return wt(e,"previousSibling")},nextUntil:function(e,t,n){return wt(e,"nextSibling",n)
},prevUntil:function(e,t,n){return wt(e,"previousSibling",n)},siblings:function(e){return Tt((e.parentNode||{}).firstChild,e)
},children:function(e){return Tt(e.firstChild)},contents:function(e){return i(e,"iframe")?e.contentDocument:(i(e,"template")&&(e=e.content||e),gt.merge([],e.childNodes))
}},function(e,t){gt.fn[e]=function(n,r){var i=gt.map(this,t,n);return"Until"!==e.slice(-5)&&(r=n),r&&"string"==typeof r&&(i=gt.filter(r,i)),this.length>1&&(At[e]||gt.uniqueSort(i),jt.test(e)&&i.reverse()),this.pushStack(i)
}});var qt=/[^\x20\t\r\n\f]+/g;gt.Callbacks=function(e){e="string"==typeof e?s(e):gt.extend({},e);var t,n,r,i,o=[],a=[],u=-1,l=function(){for(i=i||e.once,r=t=!0;a.length;u=-1)for(n=a.shift();++u<o.length;)o[u].apply(n[0],n[1])===!1&&e.stopOnFalse&&(u=o.length,n=!1);
e.memory||(n=!1),t=!1,i&&(o=n?[]:"")},c={add:function(){return o&&(n&&!t&&(u=o.length-1,a.push(n)),function r(t){gt.each(t,function(t,n){gt.isFunction(n)?e.unique&&c.has(n)||o.push(n):n&&n.length&&"string"!==gt.type(n)&&r(n)
})}(arguments),n&&!t&&l()),this},remove:function(){return gt.each(arguments,function(e,t){for(var n;(n=gt.inArray(t,o,n))>-1;)o.splice(n,1),u>=n&&u--
}),this},has:function(e){return e?gt.inArray(e,o)>-1:o.length>0},empty:function(){return o&&(o=[]),this},disable:function(){return i=a=[],o=n="",this
},disabled:function(){return!o},lock:function(){return i=a=[],n||t||(o=n=""),this},locked:function(){return!!i},fireWith:function(e,n){return i||(n=n||[],n=[e,n.slice?n.slice():n],a.push(n),t||l()),this
},fire:function(){return c.fireWith(this,arguments),this},fired:function(){return!!r}};return c},gt.extend({Deferred:function(t){var n=[["notify","progress",gt.Callbacks("memory"),gt.Callbacks("memory"),2],["resolve","done",gt.Callbacks("once memory"),gt.Callbacks("once memory"),0,"resolved"],["reject","fail",gt.Callbacks("once memory"),gt.Callbacks("once memory"),1,"rejected"]],r="pending",i={state:function(){return r
},always:function(){return o.done(arguments).fail(arguments),this},"catch":function(e){return i.then(null,e)},pipe:function(){var e=arguments;
return gt.Deferred(function(t){gt.each(n,function(n,r){var i=gt.isFunction(e[r[4]])&&e[r[4]];o[r[1]](function(){var e=i&&i.apply(this,arguments);
e&&gt.isFunction(e.promise)?e.promise().progress(t.notify).done(t.resolve).fail(t.reject):t[r[0]+"With"](this,i?[e]:arguments)
})}),e=null}).promise()},then:function(t,r,i){function o(t,n,r,i){return function(){var s=this,c=arguments,f=function(){var e,f;
if(!(a>t)){if(e=r.apply(s,c),e===n.promise())throw new TypeError("Thenable self-resolution");f=e&&("object"==typeof e||"function"==typeof e)&&e.then,gt.isFunction(f)?i?f.call(e,o(a,n,u,i),o(a,n,l,i)):(a++,f.call(e,o(a,n,u,i),o(a,n,l,i),o(a,n,u,n.notifyWith))):(r!==u&&(s=void 0,c=[e]),(i||n.resolveWith)(s,c))
}},p=i?f:function(){try{f()}catch(e){gt.Deferred.exceptionHook&&gt.Deferred.exceptionHook(e,p.stackTrace),t+1>=a&&(r!==l&&(s=void 0,c=[e]),n.rejectWith(s,c))
}};t?p():(gt.Deferred.getStackHook&&(p.stackTrace=gt.Deferred.getStackHook()),e.setTimeout(p))}}var a=0;return gt.Deferred(function(e){n[0][3].add(o(0,e,gt.isFunction(i)?i:u,e.notifyWith)),n[1][3].add(o(0,e,gt.isFunction(t)?t:u)),n[2][3].add(o(0,e,gt.isFunction(r)?r:l))
}).promise()},promise:function(e){return null!=e?gt.extend(e,i):i}},o={};return gt.each(n,function(e,t){var a=t[2],s=t[5];
i[t[1]]=a.add,s&&a.add(function(){r=s},n[3-e][2].disable,n[0][2].lock),a.add(t[3].fire),o[t[0]]=function(){return o[t[0]+"With"](this===o?void 0:this,arguments),this
},o[t[0]+"With"]=a.fireWith}),i.promise(o),t&&t.call(o,o),o},when:function(e){var t=arguments.length,n=t,r=Array(n),i=it.call(arguments),o=gt.Deferred(),a=function(e){return function(n){r[e]=this,i[e]=arguments.length>1?it.call(arguments):n,--t||o.resolveWith(r,i)
}};if(1>=t&&(c(e,o.done(a(n)).resolve,o.reject,!t),"pending"===o.state()||gt.isFunction(i[n]&&i[n].then)))return o.then();
for(;n--;)c(i[n],a(n),o.reject);return o.promise()}});var Lt=/^(Eval|Internal|Range|Reference|Syntax|Type|URI)Error$/;gt.Deferred.exceptionHook=function(t,n){e.console&&e.console.warn&&t&&Lt.test(t.name)&&e.console.warn("jQuery.Deferred exception: "+t.message,t.stack,n)
},gt.readyException=function(t){e.setTimeout(function(){throw t})};var Ht=gt.Deferred();gt.fn.ready=function(e){return Ht.then(e).catch(function(e){gt.readyException(e)
}),this},gt.extend({isReady:!1,readyWait:1,ready:function(e){(e===!0?--gt.readyWait:gt.isReady)||(gt.isReady=!0,e!==!0&&--gt.readyWait>0||Ht.resolveWith(nt,[gt]))
}}),gt.ready.then=Ht.then,"complete"===nt.readyState||"loading"!==nt.readyState&&!nt.documentElement.doScroll?e.setTimeout(gt.ready):(nt.addEventListener("DOMContentLoaded",f),e.addEventListener("load",f));
var Ft=function(e,t,n,r,i,o,a){var s=0,u=e.length,l=null==n;if("object"===gt.type(n)){i=!0;for(s in n)Ft(e,t,s,n[s],!0,o,a)
}else if(void 0!==r&&(i=!0,gt.isFunction(r)||(a=!0),l&&(a?(t.call(e,r),t=null):(l=t,t=function(e,t,n){return l.call(gt(e),n)
})),t))for(;u>s;s++)t(e[s],n,a?r:r.call(e[s],s,t(e[s],n)));return i?e:l?t.call(e):u?t(e[0],n):o},Ot=function(e){return 1===e.nodeType||9===e.nodeType||!+e.nodeType
};p.uid=1,p.prototype={cache:function(e){var t=e[this.expando];return t||(t={},Ot(e)&&(e.nodeType?e[this.expando]=t:Object.defineProperty(e,this.expando,{value:t,configurable:!0}))),t
},set:function(e,t,n){var r,i=this.cache(e);if("string"==typeof t)i[gt.camelCase(t)]=n;else for(r in t)i[gt.camelCase(r)]=t[r];
return i},get:function(e,t){return void 0===t?this.cache(e):e[this.expando]&&e[this.expando][gt.camelCase(t)]},access:function(e,t,n){return void 0===t||t&&"string"==typeof t&&void 0===n?this.get(e,t):(this.set(e,t,n),void 0!==n?n:t)
},remove:function(e,t){var n,r=e[this.expando];if(void 0!==r){if(void 0!==t){Array.isArray(t)?t=t.map(gt.camelCase):(t=gt.camelCase(t),t=t in r?[t]:t.match(qt)||[]),n=t.length;
for(;n--;)delete r[t[n]]}(void 0===t||gt.isEmptyObject(r))&&(e.nodeType?e[this.expando]=void 0:delete e[this.expando])}},hasData:function(e){var t=e[this.expando];
return void 0!==t&&!gt.isEmptyObject(t)}};var Pt=new p,Rt=new p,Mt=/^(?:\{[\w\W]*\}|\[[\w\W]*\])$/,It=/[A-Z]/g;gt.extend({hasData:function(e){return Rt.hasData(e)||Pt.hasData(e)
},data:function(e,t,n){return Rt.access(e,t,n)},removeData:function(e,t){Rt.remove(e,t)},_data:function(e,t,n){return Pt.access(e,t,n)
},_removeData:function(e,t){Pt.remove(e,t)}}),gt.fn.extend({data:function(e,t){var n,r,i,o=this[0],a=o&&o.attributes;if(void 0===e){if(this.length&&(i=Rt.get(o),1===o.nodeType&&!Pt.get(o,"hasDataAttrs"))){for(n=a.length;n--;)a[n]&&(r=a[n].name,0===r.indexOf("data-")&&(r=gt.camelCase(r.slice(5)),h(o,r,i[r])));
Pt.set(o,"hasDataAttrs",!0)}return i}return"object"==typeof e?this.each(function(){Rt.set(this,e)}):Ft(this,function(t){var n;
if(o&&void 0===t){if(n=Rt.get(o,e),void 0!==n)return n;if(n=h(o,e),void 0!==n)return n}else this.each(function(){Rt.set(this,e,t)
})},null,t,arguments.length>1,null,!0)},removeData:function(e){return this.each(function(){Rt.remove(this,e)})}}),gt.extend({queue:function(e,t,n){var r;
return e?(t=(t||"fx")+"queue",r=Pt.get(e,t),n&&(!r||Array.isArray(n)?r=Pt.access(e,t,gt.makeArray(n)):r.push(n)),r||[]):void 0
},dequeue:function(e,t){t=t||"fx";var n=gt.queue(e,t),r=n.length,i=n.shift(),o=gt._queueHooks(e,t),a=function(){gt.dequeue(e,t)
};"inprogress"===i&&(i=n.shift(),r--),i&&("fx"===t&&n.unshift("inprogress"),delete o.stop,i.call(e,a,o)),!r&&o&&o.empty.fire()
},_queueHooks:function(e,t){var n=t+"queueHooks";return Pt.get(e,n)||Pt.access(e,n,{empty:gt.Callbacks("once memory").add(function(){Pt.remove(e,[t+"queue",n])
})})}}),gt.fn.extend({queue:function(e,t){var n=2;return"string"!=typeof e&&(t=e,e="fx",n--),arguments.length<n?gt.queue(this[0],e):void 0===t?this:this.each(function(){var n=gt.queue(this,e,t);
gt._queueHooks(this,e),"fx"===e&&"inprogress"!==n[0]&&gt.dequeue(this,e)})},dequeue:function(e){return this.each(function(){gt.dequeue(this,e)
})},clearQueue:function(e){return this.queue(e||"fx",[])},promise:function(e,t){var n,r=1,i=gt.Deferred(),o=this,a=this.length,s=function(){--r||i.resolveWith(o,[o])
};for("string"!=typeof e&&(t=e,e=void 0),e=e||"fx";a--;)n=Pt.get(o[a],e+"queueHooks"),n&&n.empty&&(r++,n.empty.add(s));return s(),i.promise(t)
}});var Wt=/[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source,$t=new RegExp("^(?:([+-])=|)("+Wt+")([a-z%]*)$","i"),Bt=["Top","Right","Bottom","Left"],_t=function(e,t){return e=t||e,"none"===e.style.display||""===e.style.display&&gt.contains(e.ownerDocument,e)&&"none"===gt.css(e,"display")
},zt=function(e,t,n,r){var i,o,a={};for(o in t)a[o]=e.style[o],e.style[o]=t[o];i=n.apply(e,r||[]);for(o in t)e.style[o]=a[o];
return i},Xt={};gt.fn.extend({show:function(){return v(this,!0)},hide:function(){return v(this)},toggle:function(e){return"boolean"==typeof e?e?this.show():this.hide():this.each(function(){_t(this)?gt(this).show():gt(this).hide()
})}});var Ut=/^(?:checkbox|radio)$/i,Vt=/<([a-z][^\/\0>\x20\t\r\n\f]+)/i,Gt=/^$|\/(?:java|ecma)script/i,Yt={option:[1,"<select multiple='multiple'>","</select>"],thead:[1,"<table>","</table>"],col:[2,"<table><colgroup>","</colgroup></table>"],tr:[2,"<table><tbody>","</tbody></table>"],td:[3,"<table><tbody><tr>","</tr></tbody></table>"],_default:[0,"",""]};
Yt.optgroup=Yt.option,Yt.tbody=Yt.tfoot=Yt.colgroup=Yt.caption=Yt.thead,Yt.th=Yt.td;var Qt=/<|&#?\w+;/;!function(){var e=nt.createDocumentFragment(),t=e.appendChild(nt.createElement("div")),n=nt.createElement("input");
n.setAttribute("type","radio"),n.setAttribute("checked","checked"),n.setAttribute("name","t"),t.appendChild(n),dt.checkClone=t.cloneNode(!0).cloneNode(!0).lastChild.checked,t.innerHTML="<textarea>x</textarea>",dt.noCloneChecked=!!t.cloneNode(!0).lastChild.defaultValue
}();var Jt=nt.documentElement,Kt=/^key/,Zt=/^(?:mouse|pointer|contextmenu|drag|drop)|click/,en=/^([^.]*)(?:\.(.+)|)/;gt.event={global:{},add:function(e,t,n,r,i){var o,a,s,u,l,c,f,p,d,h,g,m=Pt.get(e);
if(m)for(n.handler&&(o=n,n=o.handler,i=o.selector),i&&gt.find.matchesSelector(Jt,i),n.guid||(n.guid=gt.guid++),(u=m.events)||(u=m.events={}),(a=m.handle)||(a=m.handle=function(t){return"undefined"!=typeof gt&&gt.event.triggered!==t.type?gt.event.dispatch.apply(e,arguments):void 0
}),t=(t||"").match(qt)||[""],l=t.length;l--;)s=en.exec(t[l])||[],d=g=s[1],h=(s[2]||"").split(".").sort(),d&&(f=gt.event.special[d]||{},d=(i?f.delegateType:f.bindType)||d,f=gt.event.special[d]||{},c=gt.extend({type:d,origType:g,data:r,handler:n,guid:n.guid,selector:i,needsContext:i&&gt.expr.match.needsContext.test(i),namespace:h.join(".")},o),(p=u[d])||(p=u[d]=[],p.delegateCount=0,f.setup&&f.setup.call(e,r,h,a)!==!1||e.addEventListener&&e.addEventListener(d,a)),f.add&&(f.add.call(e,c),c.handler.guid||(c.handler.guid=n.guid)),i?p.splice(p.delegateCount++,0,c):p.push(c),gt.event.global[d]=!0)
},remove:function(e,t,n,r,i){var o,a,s,u,l,c,f,p,d,h,g,m=Pt.hasData(e)&&Pt.get(e);if(m&&(u=m.events)){for(t=(t||"").match(qt)||[""],l=t.length;l--;)if(s=en.exec(t[l])||[],d=g=s[1],h=(s[2]||"").split(".").sort(),d){for(f=gt.event.special[d]||{},d=(r?f.delegateType:f.bindType)||d,p=u[d]||[],s=s[2]&&new RegExp("(^|\\.)"+h.join("\\.(?:.*\\.|)")+"(\\.|$)"),a=o=p.length;o--;)c=p[o],!i&&g!==c.origType||n&&n.guid!==c.guid||s&&!s.test(c.namespace)||r&&r!==c.selector&&("**"!==r||!c.selector)||(p.splice(o,1),c.selector&&p.delegateCount--,f.remove&&f.remove.call(e,c));
a&&!p.length&&(f.teardown&&f.teardown.call(e,h,m.handle)!==!1||gt.removeEvent(e,d,m.handle),delete u[d])}else for(d in u)gt.event.remove(e,d+t[l],n,r,!0);
gt.isEmptyObject(u)&&Pt.remove(e,"handle events")}},dispatch:function(e){var t,n,r,i,o,a,s=gt.event.fix(e),u=new Array(arguments.length),l=(Pt.get(this,"events")||{})[s.type]||[],c=gt.event.special[s.type]||{};
for(u[0]=s,t=1;t<arguments.length;t++)u[t]=arguments[t];if(s.delegateTarget=this,!c.preDispatch||c.preDispatch.call(this,s)!==!1){for(a=gt.event.handlers.call(this,s,l),t=0;(i=a[t++])&&!s.isPropagationStopped();)for(s.currentTarget=i.elem,n=0;(o=i.handlers[n++])&&!s.isImmediatePropagationStopped();)(!s.rnamespace||s.rnamespace.test(o.namespace))&&(s.handleObj=o,s.data=o.data,r=((gt.event.special[o.origType]||{}).handle||o.handler).apply(i.elem,u),void 0!==r&&(s.result=r)===!1&&(s.preventDefault(),s.stopPropagation()));
return c.postDispatch&&c.postDispatch.call(this,s),s.result}},handlers:function(e,t){var n,r,i,o,a,s=[],u=t.delegateCount,l=e.target;
if(u&&l.nodeType&&!("click"===e.type&&e.button>=1))for(;l!==this;l=l.parentNode||this)if(1===l.nodeType&&("click"!==e.type||l.disabled!==!0)){for(o=[],a={},n=0;u>n;n++)r=t[n],i=r.selector+" ",void 0===a[i]&&(a[i]=r.needsContext?gt(i,this).index(l)>-1:gt.find(i,this,null,[l]).length),a[i]&&o.push(r);
o.length&&s.push({elem:l,handlers:o})}return l=this,u<t.length&&s.push({elem:l,handlers:t.slice(u)}),s},addProp:function(e,t){Object.defineProperty(gt.Event.prototype,e,{enumerable:!0,configurable:!0,get:gt.isFunction(t)?function(){return this.originalEvent?t(this.originalEvent):void 0
}:function(){return this.originalEvent?this.originalEvent[e]:void 0},set:function(t){Object.defineProperty(this,e,{enumerable:!0,configurable:!0,writable:!0,value:t})
}})},fix:function(e){return e[gt.expando]?e:new gt.Event(e)},special:{load:{noBubble:!0},focus:{trigger:function(){return this!==C()&&this.focus?(this.focus(),!1):void 0
},delegateType:"focusin"},blur:{trigger:function(){return this===C()&&this.blur?(this.blur(),!1):void 0},delegateType:"focusout"},click:{trigger:function(){return"checkbox"===this.type&&this.click&&i(this,"input")?(this.click(),!1):void 0
},_default:function(e){return i(e.target,"a")}},beforeunload:{postDispatch:function(e){void 0!==e.result&&e.originalEvent&&(e.originalEvent.returnValue=e.result)
}}}},gt.removeEvent=function(e,t,n){e.removeEventListener&&e.removeEventListener(t,n)},gt.Event=function(e,t){return this instanceof gt.Event?(e&&e.type?(this.originalEvent=e,this.type=e.type,this.isDefaultPrevented=e.defaultPrevented||void 0===e.defaultPrevented&&e.returnValue===!1?w:T,this.target=e.target&&3===e.target.nodeType?e.target.parentNode:e.target,this.currentTarget=e.currentTarget,this.relatedTarget=e.relatedTarget):this.type=e,t&&gt.extend(this,t),this.timeStamp=e&&e.timeStamp||gt.now(),void(this[gt.expando]=!0)):new gt.Event(e,t)
},gt.Event.prototype={constructor:gt.Event,isDefaultPrevented:T,isPropagationStopped:T,isImmediatePropagationStopped:T,isSimulated:!1,preventDefault:function(){var e=this.originalEvent;
this.isDefaultPrevented=w,e&&!this.isSimulated&&e.preventDefault()},stopPropagation:function(){var e=this.originalEvent;this.isPropagationStopped=w,e&&!this.isSimulated&&e.stopPropagation()
},stopImmediatePropagation:function(){var e=this.originalEvent;this.isImmediatePropagationStopped=w,e&&!this.isSimulated&&e.stopImmediatePropagation(),this.stopPropagation()
}},gt.each({altKey:!0,bubbles:!0,cancelable:!0,changedTouches:!0,ctrlKey:!0,detail:!0,eventPhase:!0,metaKey:!0,pageX:!0,pageY:!0,shiftKey:!0,view:!0,"char":!0,charCode:!0,key:!0,keyCode:!0,button:!0,buttons:!0,clientX:!0,clientY:!0,offsetX:!0,offsetY:!0,pointerId:!0,pointerType:!0,screenX:!0,screenY:!0,targetTouches:!0,toElement:!0,touches:!0,which:function(e){var t=e.button;
return null==e.which&&Kt.test(e.type)?null!=e.charCode?e.charCode:e.keyCode:!e.which&&void 0!==t&&Zt.test(e.type)?1&t?1:2&t?3:4&t?2:0:e.which
}},gt.event.addProp),gt.each({mouseenter:"mouseover",mouseleave:"mouseout",pointerenter:"pointerover",pointerleave:"pointerout"},function(e,t){gt.event.special[e]={delegateType:t,bindType:t,handle:function(e){var n,r=this,i=e.relatedTarget,o=e.handleObj;
return(!i||i!==r&&!gt.contains(r,i))&&(e.type=o.origType,n=o.handler.apply(this,arguments),e.type=t),n}}}),gt.fn.extend({on:function(e,t,n,r){return E(this,e,t,n,r)
},one:function(e,t,n,r){return E(this,e,t,n,r,1)},off:function(e,t,n){var r,i;if(e&&e.preventDefault&&e.handleObj)return r=e.handleObj,gt(e.delegateTarget).off(r.namespace?r.origType+"."+r.namespace:r.origType,r.selector,r.handler),this;
if("object"==typeof e){for(i in e)this.off(i,t,e[i]);return this}return(t===!1||"function"==typeof t)&&(n=t,t=void 0),n===!1&&(n=T),this.each(function(){gt.event.remove(this,e,n,t)
})}});var tn=/<(?!area|br|col|embed|hr|img|input|link|meta|param)(([a-z][^\/\0>\x20\t\r\n\f]*)[^>]*)\/>/gi,nn=/<script|<style|<link/i,rn=/checked\s*(?:[^=]|=\s*.checked.)/i,on=/^true\/(.*)/,an=/^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g;
gt.extend({htmlPrefilter:function(e){return e.replace(tn,"<$1></$2>")},clone:function(e,t,n){var r,i,o,a,s=e.cloneNode(!0),u=gt.contains(e.ownerDocument,e);
if(!(dt.noCloneChecked||1!==e.nodeType&&11!==e.nodeType||gt.isXMLDoc(e)))for(a=y(s),o=y(e),r=0,i=o.length;i>r;r++)j(o[r],a[r]);
if(t)if(n)for(o=o||y(e),a=a||y(s),r=0,i=o.length;i>r;r++)D(o[r],a[r]);else D(e,s);return a=y(s,"script"),a.length>0&&x(a,!u&&y(e,"script")),s
},cleanData:function(e){for(var t,n,r,i=gt.event.special,o=0;void 0!==(n=e[o]);o++)if(Ot(n)){if(t=n[Pt.expando]){if(t.events)for(r in t.events)i[r]?gt.event.remove(n,r):gt.removeEvent(n,r,t.handle);
n[Pt.expando]=void 0}n[Rt.expando]&&(n[Rt.expando]=void 0)}}}),gt.fn.extend({detach:function(e){return q(this,e,!0)},remove:function(e){return q(this,e)
},text:function(e){return Ft(this,function(e){return void 0===e?gt.text(this):this.empty().each(function(){(1===this.nodeType||11===this.nodeType||9===this.nodeType)&&(this.textContent=e)
})},null,e,arguments.length)},append:function(){return A(this,arguments,function(e){if(1===this.nodeType||11===this.nodeType||9===this.nodeType){var t=k(this,e);
t.appendChild(e)}})},prepend:function(){return A(this,arguments,function(e){if(1===this.nodeType||11===this.nodeType||9===this.nodeType){var t=k(this,e);
t.insertBefore(e,t.firstChild)}})},before:function(){return A(this,arguments,function(e){this.parentNode&&this.parentNode.insertBefore(e,this)
})},after:function(){return A(this,arguments,function(e){this.parentNode&&this.parentNode.insertBefore(e,this.nextSibling)
})},empty:function(){for(var e,t=0;null!=(e=this[t]);t++)1===e.nodeType&&(gt.cleanData(y(e,!1)),e.textContent="");return this
},clone:function(e,t){return e=null==e?!1:e,t=null==t?e:t,this.map(function(){return gt.clone(this,e,t)})},html:function(e){return Ft(this,function(e){var t=this[0]||{},n=0,r=this.length;
if(void 0===e&&1===t.nodeType)return t.innerHTML;if("string"==typeof e&&!nn.test(e)&&!Yt[(Vt.exec(e)||["",""])[1].toLowerCase()]){e=gt.htmlPrefilter(e);
try{for(;r>n;n++)t=this[n]||{},1===t.nodeType&&(gt.cleanData(y(t,!1)),t.innerHTML=e);t=0}catch(i){}}t&&this.empty().append(e)
},null,e,arguments.length)},replaceWith:function(){var e=[];return A(this,arguments,function(t){var n=this.parentNode;gt.inArray(this,e)<0&&(gt.cleanData(y(this)),n&&n.replaceChild(t,this))
},e)}}),gt.each({appendTo:"append",prependTo:"prepend",insertBefore:"before",insertAfter:"after",replaceAll:"replaceWith"},function(e,t){gt.fn[e]=function(e){for(var n,r=[],i=gt(e),o=i.length-1,a=0;o>=a;a++)n=a===o?this:this.clone(!0),gt(i[a])[t](n),at.apply(r,n.get());
return this.pushStack(r)}});var sn=/^margin/,un=new RegExp("^("+Wt+")(?!px)[a-z%]+$","i"),ln=function(t){var n=t.ownerDocument.defaultView;
return n&&n.opener||(n=e),n.getComputedStyle(t)};!function(){function t(){if(s){s.style.cssText="box-sizing:border-box;position:relative;display:block;margin:auto;border:1px;padding:1px;top:1%;width:50%",s.innerHTML="",Jt.appendChild(a);
var t=e.getComputedStyle(s);n="1%"!==t.top,o="2px"===t.marginLeft,r="4px"===t.width,s.style.marginRight="50%",i="4px"===t.marginRight,Jt.removeChild(a),s=null
}}var n,r,i,o,a=nt.createElement("div"),s=nt.createElement("div");s.style&&(s.style.backgroundClip="content-box",s.cloneNode(!0).style.backgroundClip="",dt.clearCloneStyle="content-box"===s.style.backgroundClip,a.style.cssText="border:0;width:8px;height:0;top:0;left:-9999px;padding:0;margin-top:1px;position:absolute",a.appendChild(s),gt.extend(dt,{pixelPosition:function(){return t(),n
},boxSizingReliable:function(){return t(),r},pixelMarginRight:function(){return t(),i},reliableMarginLeft:function(){return t(),o
}}))}();var cn=/^(none|table(?!-c[ea]).+)/,fn=/^--/,pn={position:"absolute",visibility:"hidden",display:"block"},dn={letterSpacing:"0",fontWeight:"400"},hn=["Webkit","Moz","ms"],gn=nt.createElement("div").style;
gt.extend({cssHooks:{opacity:{get:function(e,t){if(t){var n=L(e,"opacity");return""===n?"1":n}}}},cssNumber:{animationIterationCount:!0,columnCount:!0,fillOpacity:!0,flexGrow:!0,flexShrink:!0,fontWeight:!0,lineHeight:!0,opacity:!0,order:!0,orphans:!0,widows:!0,zIndex:!0,zoom:!0},cssProps:{"float":"cssFloat"},style:function(e,t,n,r){if(e&&3!==e.nodeType&&8!==e.nodeType&&e.style){var i,o,a,s=gt.camelCase(t),u=fn.test(t),l=e.style;
return u||(t=O(s)),a=gt.cssHooks[t]||gt.cssHooks[s],void 0===n?a&&"get"in a&&void 0!==(i=a.get(e,!1,r))?i:l[t]:(o=typeof n,"string"===o&&(i=$t.exec(n))&&i[1]&&(n=g(e,t,i),o="number"),null!=n&&n===n&&("number"===o&&(n+=i&&i[3]||(gt.cssNumber[s]?"":"px")),dt.clearCloneStyle||""!==n||0!==t.indexOf("background")||(l[t]="inherit"),a&&"set"in a&&void 0===(n=a.set(e,n,r))||(u?l.setProperty(t,n):l[t]=n)),void 0)
}},css:function(e,t,n,r){var i,o,a,s=gt.camelCase(t),u=fn.test(t);return u||(t=O(s)),a=gt.cssHooks[t]||gt.cssHooks[s],a&&"get"in a&&(i=a.get(e,!0,n)),void 0===i&&(i=L(e,t,r)),"normal"===i&&t in dn&&(i=dn[t]),""===n||n?(o=parseFloat(i),n===!0||isFinite(o)?o||0:i):i
}}),gt.each(["height","width"],function(e,t){gt.cssHooks[t]={get:function(e,n,r){return n?!cn.test(gt.css(e,"display"))||e.getClientRects().length&&e.getBoundingClientRect().width?M(e,t,r):zt(e,pn,function(){return M(e,t,r)
}):void 0},set:function(e,n,r){var i,o=r&&ln(e),a=r&&R(e,t,r,"border-box"===gt.css(e,"boxSizing",!1,o),o);return a&&(i=$t.exec(n))&&"px"!==(i[3]||"px")&&(e.style[t]=n,n=gt.css(e,t)),P(e,n,a)
}}}),gt.cssHooks.marginLeft=H(dt.reliableMarginLeft,function(e,t){return t?(parseFloat(L(e,"marginLeft"))||e.getBoundingClientRect().left-zt(e,{marginLeft:0},function(){return e.getBoundingClientRect().left
}))+"px":void 0}),gt.each({margin:"",padding:"",border:"Width"},function(e,t){gt.cssHooks[e+t]={expand:function(n){for(var r=0,i={},o="string"==typeof n?n.split(" "):[n];4>r;r++)i[e+Bt[r]+t]=o[r]||o[r-2]||o[0];
return i}},sn.test(e)||(gt.cssHooks[e+t].set=P)}),gt.fn.extend({css:function(e,t){return Ft(this,function(e,t,n){var r,i,o={},a=0;
if(Array.isArray(t)){for(r=ln(e),i=t.length;i>a;a++)o[t[a]]=gt.css(e,t[a],!1,r);return o}return void 0!==n?gt.style(e,t,n):gt.css(e,t)
},e,t,arguments.length>1)}}),gt.Tween=I,I.prototype={constructor:I,init:function(e,t,n,r,i,o){this.elem=e,this.prop=n,this.easing=i||gt.easing._default,this.options=t,this.start=this.now=this.cur(),this.end=r,this.unit=o||(gt.cssNumber[n]?"":"px")
},cur:function(){var e=I.propHooks[this.prop];return e&&e.get?e.get(this):I.propHooks._default.get(this)},run:function(e){var t,n=I.propHooks[this.prop];
return this.pos=t=this.options.duration?gt.easing[this.easing](e,this.options.duration*e,0,1,this.options.duration):e,this.now=(this.end-this.start)*t+this.start,this.options.step&&this.options.step.call(this.elem,this.now,this),n&&n.set?n.set(this):I.propHooks._default.set(this),this
}},I.prototype.init.prototype=I.prototype,I.propHooks={_default:{get:function(e){var t;return 1!==e.elem.nodeType||null!=e.elem[e.prop]&&null==e.elem.style[e.prop]?e.elem[e.prop]:(t=gt.css(e.elem,e.prop,""),t&&"auto"!==t?t:0)
},set:function(e){gt.fx.step[e.prop]?gt.fx.step[e.prop](e):1!==e.elem.nodeType||null==e.elem.style[gt.cssProps[e.prop]]&&!gt.cssHooks[e.prop]?e.elem[e.prop]=e.now:gt.style(e.elem,e.prop,e.now+e.unit)
}}},I.propHooks.scrollTop=I.propHooks.scrollLeft={set:function(e){e.elem.nodeType&&e.elem.parentNode&&(e.elem[e.prop]=e.now)
}},gt.easing={linear:function(e){return e},swing:function(e){return.5-Math.cos(e*Math.PI)/2},_default:"swing"},gt.fx=I.prototype.init,gt.fx.step={};
var mn,vn,yn=/^(?:toggle|show|hide)$/,xn=/queueHooks$/;gt.Animation=gt.extend(U,{tweeners:{"*":[function(e,t){var n=this.createTween(e,t);
return g(n.elem,e,$t.exec(t),n),n}]},tweener:function(e,t){gt.isFunction(e)?(t=e,e=["*"]):e=e.match(qt);for(var n,r=0,i=e.length;i>r;r++)n=e[r],U.tweeners[n]=U.tweeners[n]||[],U.tweeners[n].unshift(t)
},prefilters:[z],prefilter:function(e,t){t?U.prefilters.unshift(e):U.prefilters.push(e)}}),gt.speed=function(e,t,n){var r=e&&"object"==typeof e?gt.extend({},e):{complete:n||!n&&t||gt.isFunction(e)&&e,duration:e,easing:n&&t||t&&!gt.isFunction(t)&&t};
return gt.fx.off?r.duration=0:"number"!=typeof r.duration&&(r.duration=r.duration in gt.fx.speeds?gt.fx.speeds[r.duration]:gt.fx.speeds._default),(null==r.queue||r.queue===!0)&&(r.queue="fx"),r.old=r.complete,r.complete=function(){gt.isFunction(r.old)&&r.old.call(this),r.queue&&gt.dequeue(this,r.queue)
},r},gt.fn.extend({fadeTo:function(e,t,n,r){return this.filter(_t).css("opacity",0).show().end().animate({opacity:t},e,n,r)
},animate:function(e,t,n,r){var i=gt.isEmptyObject(e),o=gt.speed(t,n,r),a=function(){var t=U(this,gt.extend({},e),o);(i||Pt.get(this,"finish"))&&t.stop(!0)
};return a.finish=a,i||o.queue===!1?this.each(a):this.queue(o.queue,a)},stop:function(e,t,n){var r=function(e){var t=e.stop;
delete e.stop,t(n)};return"string"!=typeof e&&(n=t,t=e,e=void 0),t&&e!==!1&&this.queue(e||"fx",[]),this.each(function(){var t=!0,i=null!=e&&e+"queueHooks",o=gt.timers,a=Pt.get(this);
if(i)a[i]&&a[i].stop&&r(a[i]);else for(i in a)a[i]&&a[i].stop&&xn.test(i)&&r(a[i]);for(i=o.length;i--;)o[i].elem!==this||null!=e&&o[i].queue!==e||(o[i].anim.stop(n),t=!1,o.splice(i,1));
(t||!n)&&gt.dequeue(this,e)})},finish:function(e){return e!==!1&&(e=e||"fx"),this.each(function(){var t,n=Pt.get(this),r=n[e+"queue"],i=n[e+"queueHooks"],o=gt.timers,a=r?r.length:0;
for(n.finish=!0,gt.queue(this,e,[]),i&&i.stop&&i.stop.call(this,!0),t=o.length;t--;)o[t].elem===this&&o[t].queue===e&&(o[t].anim.stop(!0),o.splice(t,1));
for(t=0;a>t;t++)r[t]&&r[t].finish&&r[t].finish.call(this);delete n.finish})}}),gt.each(["toggle","show","hide"],function(e,t){var n=gt.fn[t];
gt.fn[t]=function(e,r,i){return null==e||"boolean"==typeof e?n.apply(this,arguments):this.animate(B(t,!0),e,r,i)}}),gt.each({slideDown:B("show"),slideUp:B("hide"),slideToggle:B("toggle"),fadeIn:{opacity:"show"},fadeOut:{opacity:"hide"},fadeToggle:{opacity:"toggle"}},function(e,t){gt.fn[e]=function(e,n,r){return this.animate(t,e,n,r)
}}),gt.timers=[],gt.fx.tick=function(){var e,t=0,n=gt.timers;for(mn=gt.now();t<n.length;t++)e=n[t],e()||n[t]!==e||n.splice(t--,1);
n.length||gt.fx.stop(),mn=void 0},gt.fx.timer=function(e){gt.timers.push(e),gt.fx.start()},gt.fx.interval=13,gt.fx.start=function(){vn||(vn=!0,W())
},gt.fx.stop=function(){vn=null},gt.fx.speeds={slow:600,fast:200,_default:400},gt.fn.delay=function(t,n){return t=gt.fx?gt.fx.speeds[t]||t:t,n=n||"fx",this.queue(n,function(n,r){var i=e.setTimeout(n,t);
r.stop=function(){e.clearTimeout(i)}})},function(){var e=nt.createElement("input"),t=nt.createElement("select"),n=t.appendChild(nt.createElement("option"));
e.type="checkbox",dt.checkOn=""!==e.value,dt.optSelected=n.selected,e=nt.createElement("input"),e.value="t",e.type="radio",dt.radioValue="t"===e.value
}();var bn,wn=gt.expr.attrHandle;gt.fn.extend({attr:function(e,t){return Ft(this,gt.attr,e,t,arguments.length>1)},removeAttr:function(e){return this.each(function(){gt.removeAttr(this,e)
})}}),gt.extend({attr:function(e,t,n){var r,i,o=e.nodeType;if(3!==o&&8!==o&&2!==o)return"undefined"==typeof e.getAttribute?gt.prop(e,t,n):(1===o&&gt.isXMLDoc(e)||(i=gt.attrHooks[t.toLowerCase()]||(gt.expr.match.bool.test(t)?bn:void 0)),void 0!==n?null===n?void gt.removeAttr(e,t):i&&"set"in i&&void 0!==(r=i.set(e,n,t))?r:(e.setAttribute(t,n+""),n):i&&"get"in i&&null!==(r=i.get(e,t))?r:(r=gt.find.attr(e,t),null==r?void 0:r))
},attrHooks:{type:{set:function(e,t){if(!dt.radioValue&&"radio"===t&&i(e,"input")){var n=e.value;return e.setAttribute("type",t),n&&(e.value=n),t
}}}},removeAttr:function(e,t){var n,r=0,i=t&&t.match(qt);if(i&&1===e.nodeType)for(;n=i[r++];)e.removeAttribute(n)}}),bn={set:function(e,t,n){return t===!1?gt.removeAttr(e,n):e.setAttribute(n,n),n
}},gt.each(gt.expr.match.bool.source.match(/\w+/g),function(e,t){var n=wn[t]||gt.find.attr;wn[t]=function(e,t,r){var i,o,a=t.toLowerCase();
return r||(o=wn[a],wn[a]=i,i=null!=n(e,t,r)?a:null,wn[a]=o),i}});var Tn=/^(?:input|select|textarea|button)$/i,Cn=/^(?:a|area)$/i;
gt.fn.extend({prop:function(e,t){return Ft(this,gt.prop,e,t,arguments.length>1)},removeProp:function(e){return this.each(function(){delete this[gt.propFix[e]||e]
})}}),gt.extend({prop:function(e,t,n){var r,i,o=e.nodeType;if(3!==o&&8!==o&&2!==o)return 1===o&&gt.isXMLDoc(e)||(t=gt.propFix[t]||t,i=gt.propHooks[t]),void 0!==n?i&&"set"in i&&void 0!==(r=i.set(e,n,t))?r:e[t]=n:i&&"get"in i&&null!==(r=i.get(e,t))?r:e[t]
},propHooks:{tabIndex:{get:function(e){var t=gt.find.attr(e,"tabindex");return t?parseInt(t,10):Tn.test(e.nodeName)||Cn.test(e.nodeName)&&e.href?0:-1
}}},propFix:{"for":"htmlFor","class":"className"}}),dt.optSelected||(gt.propHooks.selected={get:function(e){var t=e.parentNode;
return t&&t.parentNode&&t.parentNode.selectedIndex,null},set:function(e){var t=e.parentNode;t&&(t.selectedIndex,t.parentNode&&t.parentNode.selectedIndex)
}}),gt.each(["tabIndex","readOnly","maxLength","cellSpacing","cellPadding","rowSpan","colSpan","useMap","frameBorder","contentEditable"],function(){gt.propFix[this.toLowerCase()]=this
}),gt.fn.extend({addClass:function(e){var t,n,r,i,o,a,s,u=0;if(gt.isFunction(e))return this.each(function(t){gt(this).addClass(e.call(this,t,G(this)))
});if("string"==typeof e&&e)for(t=e.match(qt)||[];n=this[u++];)if(i=G(n),r=1===n.nodeType&&" "+V(i)+" "){for(a=0;o=t[a++];)r.indexOf(" "+o+" ")<0&&(r+=o+" ");
s=V(r),i!==s&&n.setAttribute("class",s)}return this},removeClass:function(e){var t,n,r,i,o,a,s,u=0;if(gt.isFunction(e))return this.each(function(t){gt(this).removeClass(e.call(this,t,G(this)))
});if(!arguments.length)return this.attr("class","");if("string"==typeof e&&e)for(t=e.match(qt)||[];n=this[u++];)if(i=G(n),r=1===n.nodeType&&" "+V(i)+" "){for(a=0;o=t[a++];)for(;r.indexOf(" "+o+" ")>-1;)r=r.replace(" "+o+" "," ");
s=V(r),i!==s&&n.setAttribute("class",s)}return this},toggleClass:function(e,t){var n=typeof e;return"boolean"==typeof t&&"string"===n?t?this.addClass(e):this.removeClass(e):this.each(gt.isFunction(e)?function(n){gt(this).toggleClass(e.call(this,n,G(this),t),t)
}:function(){var t,r,i,o;if("string"===n)for(r=0,i=gt(this),o=e.match(qt)||[];t=o[r++];)i.hasClass(t)?i.removeClass(t):i.addClass(t);
else(void 0===e||"boolean"===n)&&(t=G(this),t&&Pt.set(this,"__className__",t),this.setAttribute&&this.setAttribute("class",t||e===!1?"":Pt.get(this,"__className__")||""))
})},hasClass:function(e){var t,n,r=0;for(t=" "+e+" ";n=this[r++];)if(1===n.nodeType&&(" "+V(G(n))+" ").indexOf(t)>-1)return!0;
return!1}});var En=/\r/g;gt.fn.extend({val:function(e){var t,n,r,i=this[0];{if(arguments.length)return r=gt.isFunction(e),this.each(function(n){var i;
1===this.nodeType&&(i=r?e.call(this,n,gt(this).val()):e,null==i?i="":"number"==typeof i?i+="":Array.isArray(i)&&(i=gt.map(i,function(e){return null==e?"":e+""
})),t=gt.valHooks[this.type]||gt.valHooks[this.nodeName.toLowerCase()],t&&"set"in t&&void 0!==t.set(this,i,"value")||(this.value=i))
});if(i)return t=gt.valHooks[i.type]||gt.valHooks[i.nodeName.toLowerCase()],t&&"get"in t&&void 0!==(n=t.get(i,"value"))?n:(n=i.value,"string"==typeof n?n.replace(En,""):null==n?"":n)
}}}),gt.extend({valHooks:{option:{get:function(e){var t=gt.find.attr(e,"value");return null!=t?t:V(gt.text(e))}},select:{get:function(e){var t,n,r,o=e.options,a=e.selectedIndex,s="select-one"===e.type,u=s?null:[],l=s?a+1:o.length;
for(r=0>a?l:s?a:0;l>r;r++)if(n=o[r],!(!n.selected&&r!==a||n.disabled||n.parentNode.disabled&&i(n.parentNode,"optgroup"))){if(t=gt(n).val(),s)return t;
u.push(t)}return u},set:function(e,t){for(var n,r,i=e.options,o=gt.makeArray(t),a=i.length;a--;)r=i[a],(r.selected=gt.inArray(gt.valHooks.option.get(r),o)>-1)&&(n=!0);
return n||(e.selectedIndex=-1),o}}}}),gt.each(["radio","checkbox"],function(){gt.valHooks[this]={set:function(e,t){return Array.isArray(t)?e.checked=gt.inArray(gt(e).val(),t)>-1:void 0
}},dt.checkOn||(gt.valHooks[this].get=function(e){return null===e.getAttribute("value")?"on":e.value})});var kn=/^(?:focusinfocus|focusoutblur)$/;
gt.extend(gt.event,{trigger:function(t,n,r,i){var o,a,s,u,l,c,f,p=[r||nt],d=ct.call(t,"type")?t.type:t,h=ct.call(t,"namespace")?t.namespace.split("."):[];
if(a=s=r=r||nt,3!==r.nodeType&&8!==r.nodeType&&!kn.test(d+gt.event.triggered)&&(d.indexOf(".")>-1&&(h=d.split("."),d=h.shift(),h.sort()),l=d.indexOf(":")<0&&"on"+d,t=t[gt.expando]?t:new gt.Event(d,"object"==typeof t&&t),t.isTrigger=i?2:3,t.namespace=h.join("."),t.rnamespace=t.namespace?new RegExp("(^|\\.)"+h.join("\\.(?:.*\\.|)")+"(\\.|$)"):null,t.result=void 0,t.target||(t.target=r),n=null==n?[t]:gt.makeArray(n,[t]),f=gt.event.special[d]||{},i||!f.trigger||f.trigger.apply(r,n)!==!1)){if(!i&&!f.noBubble&&!gt.isWindow(r)){for(u=f.delegateType||d,kn.test(u+d)||(a=a.parentNode);a;a=a.parentNode)p.push(a),s=a;
s===(r.ownerDocument||nt)&&p.push(s.defaultView||s.parentWindow||e)}for(o=0;(a=p[o++])&&!t.isPropagationStopped();)t.type=o>1?u:f.bindType||d,c=(Pt.get(a,"events")||{})[t.type]&&Pt.get(a,"handle"),c&&c.apply(a,n),c=l&&a[l],c&&c.apply&&Ot(a)&&(t.result=c.apply(a,n),t.result===!1&&t.preventDefault());
return t.type=d,i||t.isDefaultPrevented()||f._default&&f._default.apply(p.pop(),n)!==!1||!Ot(r)||l&&gt.isFunction(r[d])&&!gt.isWindow(r)&&(s=r[l],s&&(r[l]=null),gt.event.triggered=d,r[d](),gt.event.triggered=void 0,s&&(r[l]=s)),t.result
}},simulate:function(e,t,n){var r=gt.extend(new gt.Event,n,{type:e,isSimulated:!0});gt.event.trigger(r,null,t)}}),gt.fn.extend({trigger:function(e,t){return this.each(function(){gt.event.trigger(e,t,this)
})},triggerHandler:function(e,t){var n=this[0];return n?gt.event.trigger(e,t,n,!0):void 0}}),gt.each("blur focus focusin focusout resize scroll click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup contextmenu".split(" "),function(e,t){gt.fn[t]=function(e,n){return arguments.length>0?this.on(t,null,e,n):this.trigger(t)
}}),gt.fn.extend({hover:function(e,t){return this.mouseenter(e).mouseleave(t||e)}}),dt.focusin="onfocusin"in e,dt.focusin||gt.each({focus:"focusin",blur:"focusout"},function(e,t){var n=function(e){gt.event.simulate(t,e.target,gt.event.fix(e))
};gt.event.special[t]={setup:function(){var r=this.ownerDocument||this,i=Pt.access(r,t);i||r.addEventListener(e,n,!0),Pt.access(r,t,(i||0)+1)
},teardown:function(){var r=this.ownerDocument||this,i=Pt.access(r,t)-1;i?Pt.access(r,t,i):(r.removeEventListener(e,n,!0),Pt.remove(r,t))
}}});var Sn=e.location,Nn=gt.now(),Dn=/\?/;gt.parseXML=function(t){var n;if(!t||"string"!=typeof t)return null;try{n=(new e.DOMParser).parseFromString(t,"text/xml")
}catch(r){n=void 0}return(!n||n.getElementsByTagName("parsererror").length)&&gt.error("Invalid XML: "+t),n};var jn=/\[\]$/,An=/\r?\n/g,qn=/^(?:submit|button|image|reset|file)$/i,Ln=/^(?:input|select|textarea|keygen)/i;
gt.param=function(e,t){var n,r=[],i=function(e,t){var n=gt.isFunction(t)?t():t;r[r.length]=encodeURIComponent(e)+"="+encodeURIComponent(null==n?"":n)
};if(Array.isArray(e)||e.jquery&&!gt.isPlainObject(e))gt.each(e,function(){i(this.name,this.value)});else for(n in e)Y(n,e[n],t,i);
return r.join("&")},gt.fn.extend({serialize:function(){return gt.param(this.serializeArray())},serializeArray:function(){return this.map(function(){var e=gt.prop(this,"elements");
return e?gt.makeArray(e):this}).filter(function(){var e=this.type;return this.name&&!gt(this).is(":disabled")&&Ln.test(this.nodeName)&&!qn.test(e)&&(this.checked||!Ut.test(e))
}).map(function(e,t){var n=gt(this).val();return null==n?null:Array.isArray(n)?gt.map(n,function(e){return{name:t.name,value:e.replace(An,"\r\n")}
}):{name:t.name,value:n.replace(An,"\r\n")}}).get()}});var Hn=/%20/g,Fn=/#.*$/,On=/([?&])_=[^&]*/,Pn=/^(.*?):[ \t]*([^\r\n]*)$/gm,Rn=/^(?:about|app|app-storage|.+-extension|file|res|widget):$/,Mn=/^(?:GET|HEAD)$/,In=/^\/\//,Wn={},$n={},Bn="*/".concat("*"),_n=nt.createElement("a");
_n.href=Sn.href,gt.extend({active:0,lastModified:{},etag:{},ajaxSettings:{url:Sn.href,type:"GET",isLocal:Rn.test(Sn.protocol),global:!0,processData:!0,async:!0,contentType:"application/x-www-form-urlencoded; charset=UTF-8",accepts:{"*":Bn,text:"text/plain",html:"text/html",xml:"application/xml, text/xml",json:"application/json, text/javascript"},contents:{xml:/\bxml\b/,html:/\bhtml/,json:/\bjson\b/},responseFields:{xml:"responseXML",text:"responseText",json:"responseJSON"},converters:{"* text":String,"text html":!0,"text json":JSON.parse,"text xml":gt.parseXML},flatOptions:{url:!0,context:!0}},ajaxSetup:function(e,t){return t?K(K(e,gt.ajaxSettings),t):K(gt.ajaxSettings,e)
},ajaxPrefilter:Q(Wn),ajaxTransport:Q($n),ajax:function(t,n){function r(t,n,r,s){var l,p,d,b,w,T=n;c||(c=!0,u&&e.clearTimeout(u),i=void 0,a=s||"",C.readyState=t>0?4:0,l=t>=200&&300>t||304===t,r&&(b=Z(h,C,r)),b=et(h,b,C,l),l?(h.ifModified&&(w=C.getResponseHeader("Last-Modified"),w&&(gt.lastModified[o]=w),w=C.getResponseHeader("etag"),w&&(gt.etag[o]=w)),204===t||"HEAD"===h.type?T="nocontent":304===t?T="notmodified":(T=b.state,p=b.data,d=b.error,l=!d)):(d=T,(t||!T)&&(T="error",0>t&&(t=0))),C.status=t,C.statusText=(n||T)+"",l?v.resolveWith(g,[p,T,C]):v.rejectWith(g,[C,T,d]),C.statusCode(x),x=void 0,f&&m.trigger(l?"ajaxSuccess":"ajaxError",[C,h,l?p:d]),y.fireWith(g,[C,T]),f&&(m.trigger("ajaxComplete",[C,h]),--gt.active||gt.event.trigger("ajaxStop")))
}"object"==typeof t&&(n=t,t=void 0),n=n||{};var i,o,a,s,u,l,c,f,p,d,h=gt.ajaxSetup({},n),g=h.context||h,m=h.context&&(g.nodeType||g.jquery)?gt(g):gt.event,v=gt.Deferred(),y=gt.Callbacks("once memory"),x=h.statusCode||{},b={},w={},T="canceled",C={readyState:0,getResponseHeader:function(e){var t;
if(c){if(!s)for(s={};t=Pn.exec(a);)s[t[1].toLowerCase()]=t[2];t=s[e.toLowerCase()]}return null==t?null:t},getAllResponseHeaders:function(){return c?a:null
},setRequestHeader:function(e,t){return null==c&&(e=w[e.toLowerCase()]=w[e.toLowerCase()]||e,b[e]=t),this},overrideMimeType:function(e){return null==c&&(h.mimeType=e),this
},statusCode:function(e){var t;if(e)if(c)C.always(e[C.status]);else for(t in e)x[t]=[x[t],e[t]];return this},abort:function(e){var t=e||T;
return i&&i.abort(t),r(0,t),this}};if(v.promise(C),h.url=((t||h.url||Sn.href)+"").replace(In,Sn.protocol+"//"),h.type=n.method||n.type||h.method||h.type,h.dataTypes=(h.dataType||"*").toLowerCase().match(qt)||[""],null==h.crossDomain){l=nt.createElement("a");
try{l.href=h.url,l.href=l.href,h.crossDomain=_n.protocol+"//"+_n.host!=l.protocol+"//"+l.host}catch(E){h.crossDomain=!0}}if(h.data&&h.processData&&"string"!=typeof h.data&&(h.data=gt.param(h.data,h.traditional)),J(Wn,h,n,C),c)return C;
f=gt.event&&h.global,f&&0===gt.active++&&gt.event.trigger("ajaxStart"),h.type=h.type.toUpperCase(),h.hasContent=!Mn.test(h.type),o=h.url.replace(Fn,""),h.hasContent?h.data&&h.processData&&0===(h.contentType||"").indexOf("application/x-www-form-urlencoded")&&(h.data=h.data.replace(Hn,"+")):(d=h.url.slice(o.length),h.data&&(o+=(Dn.test(o)?"&":"?")+h.data,delete h.data),h.cache===!1&&(o=o.replace(On,"$1"),d=(Dn.test(o)?"&":"?")+"_="+Nn++ +d),h.url=o+d),h.ifModified&&(gt.lastModified[o]&&C.setRequestHeader("If-Modified-Since",gt.lastModified[o]),gt.etag[o]&&C.setRequestHeader("If-None-Match",gt.etag[o])),(h.data&&h.hasContent&&h.contentType!==!1||n.contentType)&&C.setRequestHeader("Content-Type",h.contentType),C.setRequestHeader("Accept",h.dataTypes[0]&&h.accepts[h.dataTypes[0]]?h.accepts[h.dataTypes[0]]+("*"!==h.dataTypes[0]?", "+Bn+"; q=0.01":""):h.accepts["*"]);
for(p in h.headers)C.setRequestHeader(p,h.headers[p]);if(h.beforeSend&&(h.beforeSend.call(g,C,h)===!1||c))return C.abort();
if(T="abort",y.add(h.complete),C.done(h.success),C.fail(h.error),i=J($n,h,n,C)){if(C.readyState=1,f&&m.trigger("ajaxSend",[C,h]),c)return C;
h.async&&h.timeout>0&&(u=e.setTimeout(function(){C.abort("timeout")},h.timeout));try{c=!1,i.send(b,r)}catch(E){if(c)throw E;
r(-1,E)}}else r(-1,"No Transport");return C},getJSON:function(e,t,n){return gt.get(e,t,n,"json")},getScript:function(e,t){return gt.get(e,void 0,t,"script")
}}),gt.each(["get","post"],function(e,t){gt[t]=function(e,n,r,i){return gt.isFunction(n)&&(i=i||r,r=n,n=void 0),gt.ajax(gt.extend({url:e,type:t,dataType:i,data:n,success:r},gt.isPlainObject(e)&&e))
}}),gt._evalUrl=function(e){return gt.ajax({url:e,type:"GET",dataType:"script",cache:!0,async:!1,global:!1,"throws":!0})},gt.fn.extend({wrapAll:function(e){var t;
return this[0]&&(gt.isFunction(e)&&(e=e.call(this[0])),t=gt(e,this[0].ownerDocument).eq(0).clone(!0),this[0].parentNode&&t.insertBefore(this[0]),t.map(function(){for(var e=this;e.firstElementChild;)e=e.firstElementChild;
return e}).append(this)),this},wrapInner:function(e){return this.each(gt.isFunction(e)?function(t){gt(this).wrapInner(e.call(this,t))
}:function(){var t=gt(this),n=t.contents();n.length?n.wrapAll(e):t.append(e)})},wrap:function(e){var t=gt.isFunction(e);return this.each(function(n){gt(this).wrapAll(t?e.call(this,n):e)
})},unwrap:function(e){return this.parent(e).not("body").each(function(){gt(this).replaceWith(this.childNodes)}),this}}),gt.expr.pseudos.hidden=function(e){return!gt.expr.pseudos.visible(e)
},gt.expr.pseudos.visible=function(e){return!!(e.offsetWidth||e.offsetHeight||e.getClientRects().length)},gt.ajaxSettings.xhr=function(){try{return new e.XMLHttpRequest
}catch(t){}};var zn={0:200,1223:204},Xn=gt.ajaxSettings.xhr();dt.cors=!!Xn&&"withCredentials"in Xn,dt.ajax=Xn=!!Xn,gt.ajaxTransport(function(t){var n,r;
return dt.cors||Xn&&!t.crossDomain?{send:function(i,o){var a,s=t.xhr();if(s.open(t.type,t.url,t.async,t.username,t.password),t.xhrFields)for(a in t.xhrFields)s[a]=t.xhrFields[a];
t.mimeType&&s.overrideMimeType&&s.overrideMimeType(t.mimeType),t.crossDomain||i["X-Requested-With"]||(i["X-Requested-With"]="XMLHttpRequest");
for(a in i)s.setRequestHeader(a,i[a]);n=function(e){return function(){n&&(n=r=s.onload=s.onerror=s.onabort=s.onreadystatechange=null,"abort"===e?s.abort():"error"===e?"number"!=typeof s.status?o(0,"error"):o(s.status,s.statusText):o(zn[s.status]||s.status,s.statusText,"text"!==(s.responseType||"text")||"string"!=typeof s.responseText?{binary:s.response}:{text:s.responseText},s.getAllResponseHeaders()))
}},s.onload=n(),r=s.onerror=n("error"),void 0!==s.onabort?s.onabort=r:s.onreadystatechange=function(){4===s.readyState&&e.setTimeout(function(){n&&r()
})},n=n("abort");try{s.send(t.hasContent&&t.data||null)}catch(u){if(n)throw u}},abort:function(){n&&n()}}:void 0}),gt.ajaxPrefilter(function(e){e.crossDomain&&(e.contents.script=!1)
}),gt.ajaxSetup({accepts:{script:"text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"},contents:{script:/\b(?:java|ecma)script\b/},converters:{"text script":function(e){return gt.globalEval(e),e
}}}),gt.ajaxPrefilter("script",function(e){void 0===e.cache&&(e.cache=!1),e.crossDomain&&(e.type="GET")}),gt.ajaxTransport("script",function(e){if(e.crossDomain){var t,n;
return{send:function(r,i){t=gt("<script>").prop({charset:e.scriptCharset,src:e.url}).on("load error",n=function(e){t.remove(),n=null,e&&i("error"===e.type?404:200,e.type)
}),nt.head.appendChild(t[0])},abort:function(){n&&n()}}}});var Un=[],Vn=/(=)\?(?=&|$)|\?\?/;gt.ajaxSetup({jsonp:"callback",jsonpCallback:function(){var e=Un.pop()||gt.expando+"_"+Nn++;
return this[e]=!0,e}}),gt.ajaxPrefilter("json jsonp",function(t,n,r){var i,o,a,s=t.jsonp!==!1&&(Vn.test(t.url)?"url":"string"==typeof t.data&&0===(t.contentType||"").indexOf("application/x-www-form-urlencoded")&&Vn.test(t.data)&&"data");
return s||"jsonp"===t.dataTypes[0]?(i=t.jsonpCallback=gt.isFunction(t.jsonpCallback)?t.jsonpCallback():t.jsonpCallback,s?t[s]=t[s].replace(Vn,"$1"+i):t.jsonp!==!1&&(t.url+=(Dn.test(t.url)?"&":"?")+t.jsonp+"="+i),t.converters["script json"]=function(){return a||gt.error(i+" was not called"),a[0]
},t.dataTypes[0]="json",o=e[i],e[i]=function(){a=arguments},r.always(function(){void 0===o?gt(e).removeProp(i):e[i]=o,t[i]&&(t.jsonpCallback=n.jsonpCallback,Un.push(i)),a&&gt.isFunction(o)&&o(a[0]),a=o=void 0
}),"script"):void 0}),dt.createHTMLDocument=function(){var e=nt.implementation.createHTMLDocument("").body;return e.innerHTML="<form></form><form></form>",2===e.childNodes.length
}(),gt.parseHTML=function(e,t,n){if("string"!=typeof e)return[];"boolean"==typeof t&&(n=t,t=!1);var r,i,o;return t||(dt.createHTMLDocument?(t=nt.implementation.createHTMLDocument(""),r=t.createElement("base"),r.href=nt.location.href,t.head.appendChild(r)):t=nt),i=Et.exec(e),o=!n&&[],i?[t.createElement(i[1])]:(i=b([e],t,o),o&&o.length&&gt(o).remove(),gt.merge([],i.childNodes))
},gt.fn.load=function(e,t,n){var r,i,o,a=this,s=e.indexOf(" ");return s>-1&&(r=V(e.slice(s)),e=e.slice(0,s)),gt.isFunction(t)?(n=t,t=void 0):t&&"object"==typeof t&&(i="POST"),a.length>0&&gt.ajax({url:e,type:i||"GET",dataType:"html",data:t}).done(function(e){o=arguments,a.html(r?gt("<div>").append(gt.parseHTML(e)).find(r):e)
}).always(n&&function(e,t){a.each(function(){n.apply(this,o||[e.responseText,t,e])})}),this},gt.each(["ajaxStart","ajaxStop","ajaxComplete","ajaxError","ajaxSuccess","ajaxSend"],function(e,t){gt.fn[t]=function(e){return this.on(t,e)
}}),gt.expr.pseudos.animated=function(e){return gt.grep(gt.timers,function(t){return e===t.elem}).length},gt.offset={setOffset:function(e,t,n){var r,i,o,a,s,u,l,c=gt.css(e,"position"),f=gt(e),p={};
"static"===c&&(e.style.position="relative"),s=f.offset(),o=gt.css(e,"top"),u=gt.css(e,"left"),l=("absolute"===c||"fixed"===c)&&(o+u).indexOf("auto")>-1,l?(r=f.position(),a=r.top,i=r.left):(a=parseFloat(o)||0,i=parseFloat(u)||0),gt.isFunction(t)&&(t=t.call(e,n,gt.extend({},s))),null!=t.top&&(p.top=t.top-s.top+a),null!=t.left&&(p.left=t.left-s.left+i),"using"in t?t.using.call(e,p):f.css(p)
}},gt.fn.extend({offset:function(e){if(arguments.length)return void 0===e?this:this.each(function(t){gt.offset.setOffset(this,e,t)
});var t,n,r,i,o=this[0];if(o)return o.getClientRects().length?(r=o.getBoundingClientRect(),t=o.ownerDocument,n=t.documentElement,i=t.defaultView,{top:r.top+i.pageYOffset-n.clientTop,left:r.left+i.pageXOffset-n.clientLeft}):{top:0,left:0}
},position:function(){if(this[0]){var e,t,n=this[0],r={top:0,left:0};return"fixed"===gt.css(n,"position")?t=n.getBoundingClientRect():(e=this.offsetParent(),t=this.offset(),i(e[0],"html")||(r=e.offset()),r={top:r.top+gt.css(e[0],"borderTopWidth",!0),left:r.left+gt.css(e[0],"borderLeftWidth",!0)}),{top:t.top-r.top-gt.css(n,"marginTop",!0),left:t.left-r.left-gt.css(n,"marginLeft",!0)}
}},offsetParent:function(){return this.map(function(){for(var e=this.offsetParent;e&&"static"===gt.css(e,"position");)e=e.offsetParent;
return e||Jt})}}),gt.each({scrollLeft:"pageXOffset",scrollTop:"pageYOffset"},function(e,t){var n="pageYOffset"===t;gt.fn[e]=function(r){return Ft(this,function(e,r,i){var o;
return gt.isWindow(e)?o=e:9===e.nodeType&&(o=e.defaultView),void 0===i?o?o[t]:e[r]:void(o?o.scrollTo(n?o.pageXOffset:i,n?i:o.pageYOffset):e[r]=i)
},e,r,arguments.length)}}),gt.each(["top","left"],function(e,t){gt.cssHooks[t]=H(dt.pixelPosition,function(e,n){return n?(n=L(e,t),un.test(n)?gt(e).position()[t]+"px":n):void 0
})}),gt.each({Height:"height",Width:"width"},function(e,t){gt.each({padding:"inner"+e,content:t,"":"outer"+e},function(n,r){gt.fn[r]=function(i,o){var a=arguments.length&&(n||"boolean"!=typeof i),s=n||(i===!0||o===!0?"margin":"border");
return Ft(this,function(t,n,i){var o;return gt.isWindow(t)?0===r.indexOf("outer")?t["inner"+e]:t.document.documentElement["client"+e]:9===t.nodeType?(o=t.documentElement,Math.max(t.body["scroll"+e],o["scroll"+e],t.body["offset"+e],o["offset"+e],o["client"+e])):void 0===i?gt.css(t,n,s):gt.style(t,n,i,s)
},t,a?i:void 0,a)}})}),gt.fn.extend({bind:function(e,t,n){return this.on(e,null,t,n)},unbind:function(e,t){return this.off(e,null,t)
},delegate:function(e,t,n,r){return this.on(t,e,n,r)},undelegate:function(e,t,n){return 1===arguments.length?this.off(e,"**"):this.off(t,e||"**",n)
}}),gt.holdReady=function(e){e?gt.readyWait++:gt.ready(!0)},gt.isArray=Array.isArray,gt.parseJSON=JSON.parse,gt.nodeName=i,"function"==typeof define&&define.amd&&define("jquery",[],function(){return gt
});var Gn=e.jQuery,Yn=e.$;return gt.noConflict=function(t){return e.$===gt&&(e.$=Yn),t&&e.jQuery===gt&&(e.jQuery=Gn),gt},t||(e.jQuery=e.$=gt),gt
});
;!function t(n,r,e){function i(u,c){if(!r[u]){if(!n[u]){var f="function"==typeof require&&require;if(!c&&f)return f(u,!0);
if(o)return o(u,!0);var a=new Error("Cannot find module '"+u+"'");throw a.code="MODULE_NOT_FOUND",a}var s=r[u]={exports:{}};
n[u][0].call(s.exports,function(t){var r=n[u][1][t];return i(r?r:t)},s,s.exports,t,n,r,e)}return r[u].exports}for(var o="function"==typeof require&&require,u=0;u<e.length;u++)i(e[u]);
return i}({1:[function(t){(function(n){"use strict";function r(t,n,r){t[n]||Object[e](t,n,{writable:!0,configurable:!0,value:r})
}if(t(295),t(296),t(2),n._babelPolyfill)throw new Error("only one instance of babel-polyfill is allowed");n._babelPolyfill=!0;
var e="defineProperty";r(String.prototype,"padLeft","".padStart),r(String.prototype,"padRight","".padEnd),"pop,reverse,shift,keys,values,entries,indexOf,every,some,forEach,map,filter,find,findIndex,includes,join,slice,concat,push,splice,unshift,sort,lastIndexOf,reduce,reduceRight,copyWithin,fill".split(",").forEach(function(t){[][t]&&r(Array,t,Function.call.bind([][t]))
})}).call(this,"undefined"!=typeof global?global:"undefined"!=typeof self?self:"undefined"!=typeof window?window:{})},{2:2,295:295,296:296}],2:[function(t,n){t(119),n.exports=t(23).RegExp.escape
},{119:119,23:23}],3:[function(t,n){n.exports=function(t){if("function"!=typeof t)throw TypeError(t+" is not a function!");
return t}},{}],4:[function(t,n){var r=t(18);n.exports=function(t,n){if("number"!=typeof t&&"Number"!=r(t))throw TypeError(n);
return+t}},{18:18}],5:[function(t,n){var r=t(117)("unscopables"),e=Array.prototype;void 0==e[r]&&t(40)(e,r,{}),n.exports=function(t){e[r][t]=!0
}},{117:117,40:40}],6:[function(t,n){n.exports=function(t,n,r,e){if(!(t instanceof n)||void 0!==e&&e in t)throw TypeError(r+": incorrect invocation!");
return t}},{}],7:[function(t,n){var r=t(49);n.exports=function(t){if(!r(t))throw TypeError(t+" is not an object!");return t
}},{49:49}],8:[function(t,n){"use strict";var r=t(109),e=t(105),i=t(108);n.exports=[].copyWithin||function(t,n){var o=r(this),u=i(o.length),c=e(t,u),f=e(n,u),a=arguments.length>2?arguments[2]:void 0,s=Math.min((void 0===a?u:e(a,u))-f,u-c),l=1;
for(c>f&&f+s>c&&(l=-1,f+=s-1,c+=s-1);s-->0;)f in o?o[c]=o[f]:delete o[c],c+=l,f+=l;return o}},{105:105,108:108,109:109}],9:[function(t,n){"use strict";
var r=t(109),e=t(105),i=t(108);n.exports=function(t){for(var n=r(this),o=i(n.length),u=arguments.length,c=e(u>1?arguments[1]:void 0,o),f=u>2?arguments[2]:void 0,a=void 0===f?o:e(f,o);a>c;)n[c++]=t;
return n}},{105:105,108:108,109:109}],10:[function(t,n){var r=t(37);n.exports=function(t,n){var e=[];return r(t,!1,e.push,e,n),e
}},{37:37}],11:[function(t,n){var r=t(107),e=t(108),i=t(105);n.exports=function(t){return function(n,o,u){var c,f=r(n),a=e(f.length),s=i(u,a);
if(t&&o!=o){for(;a>s;)if(c=f[s++],c!=c)return!0}else for(;a>s;s++)if((t||s in f)&&f[s]===o)return t||s||0;return!t&&-1}}},{105:105,107:107,108:108}],12:[function(t,n){var r=t(25),e=t(45),i=t(109),o=t(108),u=t(15);
n.exports=function(t,n){var c=1==t,f=2==t,a=3==t,s=4==t,l=6==t,h=5==t||l,v=n||u;return function(n,u,p){for(var g,d,y=i(n),m=e(y),b=r(u,p,3),w=o(m.length),x=0,_=c?v(n,w):f?v(n,0):void 0;w>x;x++)if((h||x in m)&&(g=m[x],d=b(g,x,y),t))if(c)_[x]=d;
else if(d)switch(t){case 3:return!0;case 5:return g;case 6:return x;case 2:_.push(g)}else if(s)return!1;return l?-1:a||s?s:_
}}},{108:108,109:109,15:15,25:25,45:45}],13:[function(t,n){var r=t(3),e=t(109),i=t(45),o=t(108);n.exports=function(t,n,u,c,f){r(n);
var a=e(t),s=i(a),l=o(a.length),h=f?l-1:0,v=f?-1:1;if(2>u)for(;;){if(h in s){c=s[h],h+=v;break}if(h+=v,f?0>h:h>=l)throw TypeError("Reduce of empty array with no initial value")
}for(;f?h>=0:l>h;h+=v)h in s&&(c=n(c,s[h],h,a));return c}},{108:108,109:109,3:3,45:45}],14:[function(t,n){var r=t(49),e=t(47),i=t(117)("species");
n.exports=function(t){var n;return e(t)&&(n=t.constructor,"function"!=typeof n||n!==Array&&!e(n.prototype)||(n=void 0),r(n)&&(n=n[i],null===n&&(n=void 0))),void 0===n?Array:n
}},{117:117,47:47,49:49}],15:[function(t,n){var r=t(14);n.exports=function(t,n){return new(r(t))(n)}},{14:14}],16:[function(t,n){"use strict";
var r=t(3),e=t(49),i=t(44),o=[].slice,u={},c=function(t,n,r){if(!(n in u)){for(var e=[],i=0;n>i;i++)e[i]="a["+i+"]";u[n]=Function("F,a","return new F("+e.join(",")+")")
}return u[n](t,r)};n.exports=Function.bind||function(t){var n=r(this),u=o.call(arguments,1),f=function(){var r=u.concat(o.call(arguments));
return this instanceof f?c(n,r.length,r):i(n,r,t)};return e(n.prototype)&&(f.prototype=n.prototype),f}},{3:3,44:44,49:49}],17:[function(t,n){var r=t(18),e=t(117)("toStringTag"),i="Arguments"==r(function(){return arguments
}()),o=function(t,n){try{return t[n]}catch(t){}};n.exports=function(t){var n,u,c;return void 0===t?"Undefined":null===t?"Null":"string"==typeof(u=o(n=Object(t),e))?u:i?r(n):"Object"==(c=r(n))&&"function"==typeof n.callee?"Arguments":c
}},{117:117,18:18}],18:[function(t,n){var r={}.toString;n.exports=function(t){return r.call(t).slice(8,-1)}},{}],19:[function(t,n){"use strict";
var r=t(67).f,e=t(66),i=t(86),o=t(25),u=t(6),c=t(27),f=t(37),a=t(53),s=t(55),l=t(91),h=t(28),v=t(62).fastKey,p=h?"_s":"size",g=function(t,n){var r,e=v(n);
if("F"!==e)return t._i[e];for(r=t._f;r;r=r.n)if(r.k==n)return r};n.exports={getConstructor:function(t,n,a,s){var l=t(function(t,r){u(t,l,n,"_i"),t._i=e(null),t._f=void 0,t._l=void 0,t[p]=0,void 0!=r&&f(r,a,t[s],t)
});return i(l.prototype,{clear:function(){for(var t=this,n=t._i,r=t._f;r;r=r.n)r.r=!0,r.p&&(r.p=r.p.n=void 0),delete n[r.i];
t._f=t._l=void 0,t[p]=0},"delete":function(t){var n=this,r=g(n,t);if(r){var e=r.n,i=r.p;delete n._i[r.i],r.r=!0,i&&(i.n=e),e&&(e.p=i),n._f==r&&(n._f=e),n._l==r&&(n._l=i),n[p]--
}return!!r},forEach:function(t){u(this,l,"forEach");for(var n,r=o(t,arguments.length>1?arguments[1]:void 0,3);n=n?n.n:this._f;)for(r(n.v,n.k,this);n&&n.r;)n=n.p
},has:function(t){return!!g(this,t)}}),h&&r(l.prototype,"size",{get:function(){return c(this[p])}}),l},def:function(t,n,r){var e,i,o=g(t,n);
return o?o.v=r:(t._l=o={i:i=v(n,!0),k:n,v:r,p:e=t._l,n:void 0,r:!1},t._f||(t._f=o),e&&(e.n=o),t[p]++,"F"!==i&&(t._i[i]=o)),t
},getEntry:g,setStrong:function(t,n,r){a(t,n,function(t,n){this._t=t,this._k=n,this._l=void 0},function(){for(var t=this,n=t._k,r=t._l;r&&r.r;)r=r.p;
return t._t&&(t._l=r=r?r.n:t._t._f)?"keys"==n?s(0,r.k):"values"==n?s(0,r.v):s(0,[r.k,r.v]):(t._t=void 0,s(1))},r?"entries":"values",!r,!0),l(n)
}}},{25:25,27:27,28:28,37:37,53:53,55:55,6:6,62:62,66:66,67:67,86:86,91:91}],20:[function(t,n){var r=t(17),e=t(10);n.exports=function(t){return function(){if(r(this)!=t)throw TypeError(t+"#toJSON isn't generic");
return e(this)}}},{10:10,17:17}],21:[function(t,n){"use strict";var r=t(86),e=t(62).getWeak,i=t(7),o=t(49),u=t(6),c=t(37),f=t(12),a=t(39),s=f(5),l=f(6),h=0,v=function(t){return t._l||(t._l=new p)
},p=function(){this.a=[]},g=function(t,n){return s(t.a,function(t){return t[0]===n})};p.prototype={get:function(t){var n=g(this,t);
return n?n[1]:void 0},has:function(t){return!!g(this,t)},set:function(t,n){var r=g(this,t);r?r[1]=n:this.a.push([t,n])},"delete":function(t){var n=l(this.a,function(n){return n[0]===t
});return~n&&this.a.splice(n,1),!!~n}},n.exports={getConstructor:function(t,n,i,f){var s=t(function(t,r){u(t,s,n,"_i"),t._i=h++,t._l=void 0,void 0!=r&&c(r,i,t[f],t)
});return r(s.prototype,{"delete":function(t){if(!o(t))return!1;var n=e(t);return n===!0?v(this).delete(t):n&&a(n,this._i)&&delete n[this._i]
},has:function(t){if(!o(t))return!1;var n=e(t);return n===!0?v(this).has(t):n&&a(n,this._i)}}),s},def:function(t,n,r){var o=e(i(n),!0);
return o===!0?v(t).set(n,r):o[t._i]=r,t},ufstore:v}},{12:12,37:37,39:39,49:49,6:6,62:62,7:7,86:86}],22:[function(t,n){"use strict";
var r=t(38),e=t(32),i=t(87),o=t(86),u=t(62),c=t(37),f=t(6),a=t(49),s=t(34),l=t(54),h=t(92),v=t(43);n.exports=function(t,n,p,g,d,y){var m=r[t],b=m,w=d?"set":"add",x=b&&b.prototype,_={},S=function(t){var n=x[t];
i(x,t,"delete"==t?function(t){return!(y&&!a(t))&&n.call(this,0===t?0:t)}:"has"==t?function(t){return!(y&&!a(t))&&n.call(this,0===t?0:t)
}:"get"==t?function(t){return y&&!a(t)?void 0:n.call(this,0===t?0:t)}:"add"==t?function(t){return n.call(this,0===t?0:t),this
}:function(t,r){return n.call(this,0===t?0:t,r),this})};if("function"==typeof b&&(y||x.forEach&&!s(function(){(new b).entries().next()
}))){var E=new b,O=E[w](y?{}:-0,1)!=E,F=s(function(){E.has(1)}),P=l(function(t){new b(t)}),M=!y&&s(function(){for(var t=new b,n=5;n--;)t[w](n,n);
return!t.has(-0)});P||(b=n(function(n,r){f(n,b,t);var e=v(new m,n,b);return void 0!=r&&c(r,d,e[w],e),e}),b.prototype=x,x.constructor=b),(F||M)&&(S("delete"),S("has"),d&&S("get")),(M||O)&&S(w),y&&x.clear&&delete x.clear
}else b=g.getConstructor(n,t,d,w),o(b.prototype,p),u.NEED=!0;return h(b,t),_[t]=b,e(e.G+e.W+e.F*(b!=m),_),y||g.setStrong(b,t,d),b
}},{32:32,34:34,37:37,38:38,43:43,49:49,54:54,6:6,62:62,86:86,87:87,92:92}],23:[function(t,n){var r=n.exports={version:"2.4.0"};
"number"==typeof __e&&(__e=r)},{}],24:[function(t,n){"use strict";var r=t(67),e=t(85);n.exports=function(t,n,i){n in t?r.f(t,n,e(0,i)):t[n]=i
}},{67:67,85:85}],25:[function(t,n){var r=t(3);n.exports=function(t,n,e){if(r(t),void 0===n)return t;switch(e){case 1:return function(r){return t.call(n,r)
};case 2:return function(r,e){return t.call(n,r,e)};case 3:return function(r,e,i){return t.call(n,r,e,i)}}return function(){return t.apply(n,arguments)
}}},{3:3}],26:[function(t,n){"use strict";var r=t(7),e=t(110),i="number";n.exports=function(t){if("string"!==t&&t!==i&&"default"!==t)throw TypeError("Incorrect hint");
return e(r(this),t!=i)}},{110:110,7:7}],27:[function(t,n){n.exports=function(t){if(void 0==t)throw TypeError("Can't call method on  "+t);
return t}},{}],28:[function(t,n){n.exports=!t(34)(function(){return 7!=Object.defineProperty({},"a",{get:function(){return 7
}}).a})},{34:34}],29:[function(t,n){var r=t(49),e=t(38).document,i=r(e)&&r(e.createElement);n.exports=function(t){return i?e.createElement(t):{}
}},{38:38,49:49}],30:[function(t,n){n.exports="constructor,hasOwnProperty,isPrototypeOf,propertyIsEnumerable,toLocaleString,toString,valueOf".split(",")
},{}],31:[function(t,n){var r=t(76),e=t(73),i=t(77);n.exports=function(t){var n=r(t),o=e.f;if(o)for(var u,c=o(t),f=i.f,a=0;c.length>a;)f.call(t,u=c[a++])&&n.push(u);
return n}},{73:73,76:76,77:77}],32:[function(t,n){var r=t(38),e=t(23),i=t(40),o=t(87),u=t(25),c="prototype",f=function(t,n,a){var s,l,h,v,p=t&f.F,g=t&f.G,d=t&f.S,y=t&f.P,m=t&f.B,b=g?r:d?r[n]||(r[n]={}):(r[n]||{})[c],w=g?e:e[n]||(e[n]={}),x=w[c]||(w[c]={});
g&&(a=n);for(s in a)l=!p&&b&&void 0!==b[s],h=(l?b:a)[s],v=m&&l?u(h,r):y&&"function"==typeof h?u(Function.call,h):h,b&&o(b,s,h,t&f.U),w[s]!=h&&i(w,s,v),y&&x[s]!=h&&(x[s]=h)
};r.core=e,f.F=1,f.G=2,f.S=4,f.P=8,f.B=16,f.W=32,f.U=64,f.R=128,n.exports=f},{23:23,25:25,38:38,40:40,87:87}],33:[function(t,n){var r=t(117)("match");
n.exports=function(t){var n=/./;try{"/./"[t](n)}catch(e){try{return n[r]=!1,!"/./"[t](n)}catch(t){}}return!0}},{117:117}],34:[function(t,n){n.exports=function(t){try{return!!t()
}catch(t){return!0}}},{}],35:[function(t,n){"use strict";var r=t(40),e=t(87),i=t(34),o=t(27),u=t(117);n.exports=function(t,n,c){var f=u(t),a=c(o,f,""[t]),s=a[0],l=a[1];
i(function(){var n={};return n[f]=function(){return 7},7!=""[t](n)})&&(e(String.prototype,t,s),r(RegExp.prototype,f,2==n?function(t,n){return l.call(t,this,n)
}:function(t){return l.call(t,this)}))}},{117:117,27:27,34:34,40:40,87:87}],36:[function(t,n){"use strict";var r=t(7);n.exports=function(){var t=r(this),n="";
return t.global&&(n+="g"),t.ignoreCase&&(n+="i"),t.multiline&&(n+="m"),t.unicode&&(n+="u"),t.sticky&&(n+="y"),n}},{7:7}],37:[function(t,n,r){var e=t(25),i=t(51),o=t(46),u=t(7),c=t(108),f=t(118),a={},s={},r=n.exports=function(t,n,r,l,h){var v,p,g,d,y=h?function(){return t
}:f(t),m=e(r,l,n?2:1),b=0;if("function"!=typeof y)throw TypeError(t+" is not iterable!");if(o(y)){for(v=c(t.length);v>b;b++)if(d=n?m(u(p=t[b])[0],p[1]):m(t[b]),d===a||d===s)return d
}else for(g=y.call(t);!(p=g.next()).done;)if(d=i(g,m,p.value,n),d===a||d===s)return d};r.BREAK=a,r.RETURN=s},{108:108,118:118,25:25,46:46,51:51,7:7}],38:[function(t,n){var r=n.exports="undefined"!=typeof window&&window.Math==Math?window:"undefined"!=typeof self&&self.Math==Math?self:Function("return this")();
"number"==typeof __g&&(__g=r)},{}],39:[function(t,n){var r={}.hasOwnProperty;n.exports=function(t,n){return r.call(t,n)}},{}],40:[function(t,n){var r=t(67),e=t(85);
n.exports=t(28)?function(t,n,i){return r.f(t,n,e(1,i))}:function(t,n,r){return t[n]=r,t}},{28:28,67:67,85:85}],41:[function(t,n){n.exports=t(38).document&&document.documentElement
},{38:38}],42:[function(t,n){n.exports=!t(28)&&!t(34)(function(){return 7!=Object.defineProperty(t(29)("div"),"a",{get:function(){return 7
}}).a})},{28:28,29:29,34:34}],43:[function(t,n){var r=t(49),e=t(90).set;n.exports=function(t,n,i){var o,u=n.constructor;return u!==i&&"function"==typeof u&&(o=u.prototype)!==i.prototype&&r(o)&&e&&e(t,o),t
}},{49:49,90:90}],44:[function(t,n){n.exports=function(t,n,r){var e=void 0===r;switch(n.length){case 0:return e?t():t.call(r);
case 1:return e?t(n[0]):t.call(r,n[0]);case 2:return e?t(n[0],n[1]):t.call(r,n[0],n[1]);case 3:return e?t(n[0],n[1],n[2]):t.call(r,n[0],n[1],n[2]);
case 4:return e?t(n[0],n[1],n[2],n[3]):t.call(r,n[0],n[1],n[2],n[3])}return t.apply(r,n)}},{}],45:[function(t,n){var r=t(18);
n.exports=Object("z").propertyIsEnumerable(0)?Object:function(t){return"String"==r(t)?t.split(""):Object(t)}},{18:18}],46:[function(t,n){var r=t(56),e=t(117)("iterator"),i=Array.prototype;
n.exports=function(t){return void 0!==t&&(r.Array===t||i[e]===t)}},{117:117,56:56}],47:[function(t,n){var r=t(18);n.exports=Array.isArray||function(t){return"Array"==r(t)
}},{18:18}],48:[function(t,n){var r=t(49),e=Math.floor;n.exports=function(t){return!r(t)&&isFinite(t)&&e(t)===t}},{49:49}],49:[function(t,n){n.exports=function(t){return"object"==typeof t?null!==t:"function"==typeof t
}},{}],50:[function(t,n){var r=t(49),e=t(18),i=t(117)("match");n.exports=function(t){var n;return r(t)&&(void 0!==(n=t[i])?!!n:"RegExp"==e(t))
}},{117:117,18:18,49:49}],51:[function(t,n){var r=t(7);n.exports=function(t,n,e,i){try{return i?n(r(e)[0],e[1]):n(e)}catch(n){var o=t.return;
throw void 0!==o&&r(o.call(t)),n}}},{7:7}],52:[function(t,n){"use strict";var r=t(66),e=t(85),i=t(92),o={};t(40)(o,t(117)("iterator"),function(){return this
}),n.exports=function(t,n,u){t.prototype=r(o,{next:e(1,u)}),i(t,n+" Iterator")}},{117:117,40:40,66:66,85:85,92:92}],53:[function(t,n){"use strict";
var r=t(58),e=t(32),i=t(87),o=t(40),u=t(39),c=t(56),f=t(52),a=t(92),s=t(74),l=t(117)("iterator"),h=!([].keys&&"next"in[].keys()),v="@@iterator",p="keys",g="values",d=function(){return this
};n.exports=function(t,n,y,m,b,w,x){f(y,n,m);var _,S,E,O=function(t){if(!h&&t in j)return j[t];switch(t){case p:return function(){return new y(this,t)
};case g:return function(){return new y(this,t)}}return function(){return new y(this,t)}},F=n+" Iterator",P=b==g,M=!1,j=t.prototype,A=j[l]||j[v]||b&&j[b],I=A||O(b),N=b?P?O("entries"):I:void 0,R="Array"==n?j.entries||A:A;
if(R&&(E=s(R.call(new t)),E!==Object.prototype&&(a(E,F,!0),r||u(E,l)||o(E,l,d))),P&&A&&A.name!==g&&(M=!0,I=function(){return A.call(this)
}),r&&!x||!h&&!M&&j[l]||o(j,l,I),c[n]=I,c[F]=d,b)if(_={values:P?I:O(g),keys:w?I:O(p),entries:N},x)for(S in _)S in j||i(j,S,_[S]);
else e(e.P+e.F*(h||M),n,_);return _}},{117:117,32:32,39:39,40:40,52:52,56:56,58:58,74:74,87:87,92:92}],54:[function(t,n){var r=t(117)("iterator"),e=!1;
try{var i=[7][r]();i.return=function(){e=!0},Array.from(i,function(){throw 2})}catch(t){}n.exports=function(t,n){if(!n&&!e)return!1;
var i=!1;try{var o=[7],u=o[r]();u.next=function(){return{done:i=!0}},o[r]=function(){return u},t(o)}catch(t){}return i}},{117:117}],55:[function(t,n){n.exports=function(t,n){return{value:n,done:!!t}
}},{}],56:[function(t,n){n.exports={}},{}],57:[function(t,n){var r=t(76),e=t(107);n.exports=function(t,n){for(var i,o=e(t),u=r(o),c=u.length,f=0;c>f;)if(o[i=u[f++]]===n)return i
}},{107:107,76:76}],58:[function(t,n){n.exports=!1},{}],59:[function(t,n){var r=Math.expm1;n.exports=!r||r(10)>22025.465794806718||r(10)<22025.465794806718||-2e-17!=r(-2e-17)?function(t){return 0==(t=+t)?t:t>-1e-6&&1e-6>t?t+t*t/2:Math.exp(t)-1
}:r},{}],60:[function(t,n){n.exports=Math.log1p||function(t){return(t=+t)>-1e-8&&1e-8>t?t-t*t/2:Math.log(1+t)}},{}],61:[function(t,n){n.exports=Math.sign||function(t){return 0==(t=+t)||t!=t?t:0>t?-1:1
}},{}],62:[function(t,n){var r=t(114)("meta"),e=t(49),i=t(39),o=t(67).f,u=0,c=Object.isExtensible||function(){return!0},f=!t(34)(function(){return c(Object.preventExtensions({}))
}),a=function(t){o(t,r,{value:{i:"O"+ ++u,w:{}}})},s=function(t,n){if(!e(t))return"symbol"==typeof t?t:("string"==typeof t?"S":"P")+t;
if(!i(t,r)){if(!c(t))return"F";if(!n)return"E";a(t)}return t[r].i},l=function(t,n){if(!i(t,r)){if(!c(t))return!0;if(!n)return!1;
a(t)}return t[r].w},h=function(t){return f&&v.NEED&&c(t)&&!i(t,r)&&a(t),t},v=n.exports={KEY:r,NEED:!1,fastKey:s,getWeak:l,onFreeze:h}
},{114:114,34:34,39:39,49:49,67:67}],63:[function(t,n){var r=t(149),e=t(32),i=t(94)("metadata"),o=i.store||(i.store=new(t(255))),u=function(t,n,e){var i=o.get(t);
if(!i){if(!e)return;o.set(t,i=new r)}var u=i.get(n);if(!u){if(!e)return;i.set(n,u=new r)}return u},c=function(t,n,r){var e=u(n,r,!1);
return void 0!==e&&e.has(t)},f=function(t,n,r){var e=u(n,r,!1);return void 0===e?void 0:e.get(t)},a=function(t,n,r,e){u(r,e,!0).set(t,n)
},s=function(t,n){var r=u(t,n,!1),e=[];return r&&r.forEach(function(t,n){e.push(n)}),e},l=function(t){return void 0===t||"symbol"==typeof t?t:String(t)
},h=function(t){e(e.S,"Reflect",t)};n.exports={store:o,map:u,has:c,get:f,set:a,keys:s,key:l,exp:h}},{149:149,255:255,32:32,94:94}],64:[function(t,n){var r=t(38),e=t(104).set,i=r.MutationObserver||r.WebKitMutationObserver,o=r.process,u=r.Promise,c="process"==t(18)(o);
n.exports=function(){var t,n,f,a=function(){var r,e;for(c&&(r=o.domain)&&r.exit();t;){e=t.fn,t=t.next;try{e()}catch(r){throw t?f():n=void 0,r
}}n=void 0,r&&r.enter()};if(c)f=function(){o.nextTick(a)};else if(i){var s=!0,l=document.createTextNode("");new i(a).observe(l,{characterData:!0}),f=function(){l.data=s=!s
}}else if(u&&u.resolve){var h=u.resolve();f=function(){h.then(a)}}else f=function(){e.call(r,a)};return function(r){var e={fn:r,next:void 0};
n&&(n.next=e),t||(t=e,f()),n=e}}},{104:104,18:18,38:38}],65:[function(t,n){"use strict";var r=t(76),e=t(73),i=t(77),o=t(109),u=t(45),c=Object.assign;
n.exports=!c||t(34)(function(){var t={},n={},r=Symbol(),e="abcdefghijklmnopqrst";return t[r]=7,e.split("").forEach(function(t){n[t]=t
}),7!=c({},t)[r]||Object.keys(c({},n)).join("")!=e})?function(t){for(var n=o(t),c=arguments.length,f=1,a=e.f,s=i.f;c>f;)for(var l,h=u(arguments[f++]),v=a?r(h).concat(a(h)):r(h),p=v.length,g=0;p>g;)s.call(h,l=v[g++])&&(n[l]=h[l]);
return n}:c},{109:109,34:34,45:45,73:73,76:76,77:77}],66:[function(t,n){var r=t(7),e=t(68),i=t(30),o=t(93)("IE_PROTO"),u=function(){},c="prototype",f=function(){var n,r=t(29)("iframe"),e=i.length,o="<",u=">";
for(r.style.display="none",t(41).appendChild(r),r.src="javascript:",n=r.contentWindow.document,n.open(),n.write(o+"script"+u+"document.F=Object"+o+"/script"+u),n.close(),f=n.F;e--;)delete f[c][i[e]];
return f()};n.exports=Object.create||function(t,n){var i;return null!==t?(u[c]=r(t),i=new u,u[c]=null,i[o]=t):i=f(),void 0===n?i:e(i,n)
}},{29:29,30:30,41:41,68:68,7:7,93:93}],67:[function(t,n,r){var e=t(7),i=t(42),o=t(110),u=Object.defineProperty;r.f=t(28)?Object.defineProperty:function(t,n,r){if(e(t),n=o(n,!0),e(r),i)try{return u(t,n,r)
}catch(t){}if("get"in r||"set"in r)throw TypeError("Accessors not supported!");return"value"in r&&(t[n]=r.value),t}},{110:110,28:28,42:42,7:7}],68:[function(t,n){var r=t(67),e=t(7),i=t(76);
n.exports=t(28)?Object.defineProperties:function(t,n){e(t);for(var o,u=i(n),c=u.length,f=0;c>f;)r.f(t,o=u[f++],n[o]);return t
}},{28:28,67:67,7:7,76:76}],69:[function(t,n){n.exports=t(58)||!t(34)(function(){var n=Math.random();__defineSetter__.call(null,n,function(){}),delete t(38)[n]
})},{34:34,38:38,58:58}],70:[function(t,n,r){var e=t(77),i=t(85),o=t(107),u=t(110),c=t(39),f=t(42),a=Object.getOwnPropertyDescriptor;
r.f=t(28)?a:function(t,n){if(t=o(t),n=u(n,!0),f)try{return a(t,n)}catch(t){}return c(t,n)?i(!e.f.call(t,n),t[n]):void 0}},{107:107,110:110,28:28,39:39,42:42,77:77,85:85}],71:[function(t,n){var r=t(107),e=t(72).f,i={}.toString,o="object"==typeof window&&window&&Object.getOwnPropertyNames?Object.getOwnPropertyNames(window):[],u=function(t){try{return e(t)
}catch(t){return o.slice()}};n.exports.f=function(t){return o&&"[object Window]"==i.call(t)?u(t):e(r(t))}},{107:107,72:72}],72:[function(t,n,r){var e=t(75),i=t(30).concat("length","prototype");
r.f=Object.getOwnPropertyNames||function(t){return e(t,i)}},{30:30,75:75}],73:[function(t,n,r){r.f=Object.getOwnPropertySymbols
},{}],74:[function(t,n){var r=t(39),e=t(109),i=t(93)("IE_PROTO"),o=Object.prototype;n.exports=Object.getPrototypeOf||function(t){return t=e(t),r(t,i)?t[i]:"function"==typeof t.constructor&&t instanceof t.constructor?t.constructor.prototype:t instanceof Object?o:null
}},{109:109,39:39,93:93}],75:[function(t,n){var r=t(39),e=t(107),i=t(11)(!1),o=t(93)("IE_PROTO");n.exports=function(t,n){var u,c=e(t),f=0,a=[];
for(u in c)u!=o&&r(c,u)&&a.push(u);for(;n.length>f;)r(c,u=n[f++])&&(~i(a,u)||a.push(u));return a}},{107:107,11:11,39:39,93:93}],76:[function(t,n){var r=t(75),e=t(30);
n.exports=Object.keys||function(t){return r(t,e)}},{30:30,75:75}],77:[function(t,n,r){r.f={}.propertyIsEnumerable},{}],78:[function(t,n){var r=t(32),e=t(23),i=t(34);
n.exports=function(t,n){var o=(e.Object||{})[t]||Object[t],u={};u[t]=n(o),r(r.S+r.F*i(function(){o(1)}),"Object",u)}},{23:23,32:32,34:34}],79:[function(t,n){var r=t(76),e=t(107),i=t(77).f;
n.exports=function(t){return function(n){for(var o,u=e(n),c=r(u),f=c.length,a=0,s=[];f>a;)i.call(u,o=c[a++])&&s.push(t?[o,u[o]]:u[o]);
return s}}},{107:107,76:76,77:77}],80:[function(t,n){var r=t(72),e=t(73),i=t(7),o=t(38).Reflect;n.exports=o&&o.ownKeys||function(t){var n=r.f(i(t)),o=e.f;
return o?n.concat(o(t)):n}},{38:38,7:7,72:72,73:73}],81:[function(t,n){var r=t(38).parseFloat,e=t(102).trim;n.exports=1/r(t(103)+"-0")!==-(1/0)?function(t){var n=e(String(t),3),i=r(n);
return 0===i&&"-"==n.charAt(0)?-0:i}:r},{102:102,103:103,38:38}],82:[function(t,n){var r=t(38).parseInt,e=t(102).trim,i=t(103),o=/^[\-+]?0[xX]/;
n.exports=8!==r(i+"08")||22!==r(i+"0x16")?function(t,n){var i=e(String(t),3);return r(i,n>>>0||(o.test(i)?16:10))}:r},{102:102,103:103,38:38}],83:[function(t,n){"use strict";
var r=t(84),e=t(44),i=t(3);n.exports=function(){for(var t=i(this),n=arguments.length,o=Array(n),u=0,c=r._,f=!1;n>u;)(o[u]=arguments[u++])===c&&(f=!0);
return function(){var r,i=this,u=arguments.length,a=0,s=0;if(!f&&!u)return e(t,o,i);if(r=o.slice(),f)for(;n>a;a++)r[a]===c&&(r[a]=arguments[s++]);
for(;u>s;)r.push(arguments[s++]);return e(t,r,i)}}},{3:3,44:44,84:84}],84:[function(t,n){n.exports=t(38)},{38:38}],85:[function(t,n){n.exports=function(t,n){return{enumerable:!(1&t),configurable:!(2&t),writable:!(4&t),value:n}
}},{}],86:[function(t,n){var r=t(87);n.exports=function(t,n,e){for(var i in n)r(t,i,n[i],e);return t}},{87:87}],87:[function(t,n){var r=t(38),e=t(40),i=t(39),o=t(114)("src"),u="toString",c=Function[u],f=(""+c).split(u);
t(23).inspectSource=function(t){return c.call(t)},(n.exports=function(t,n,u,c){var a="function"==typeof u;a&&(i(u,"name")||e(u,"name",n)),t[n]!==u&&(a&&(i(u,o)||e(u,o,t[n]?""+t[n]:f.join(String(n)))),t===r?t[n]=u:c?t[n]?t[n]=u:e(t,n,u):(delete t[n],e(t,n,u)))
})(Function.prototype,u,function(){return"function"==typeof this&&this[o]||c.call(this)})},{114:114,23:23,38:38,39:39,40:40}],88:[function(t,n){n.exports=function(t,n){var r=n===Object(n)?function(t){return n[t]
}:n;return function(n){return String(n).replace(t,r)}}},{}],89:[function(t,n){n.exports=Object.is||function(t,n){return t===n?0!==t||1/t===1/n:t!=t&&n!=n
}},{}],90:[function(t,n){var r=t(49),e=t(7),i=function(t,n){if(e(t),!r(n)&&null!==n)throw TypeError(n+": can't set as prototype!")
};n.exports={set:Object.setPrototypeOf||("__proto__"in{}?function(t,n,r){try{r=e(25)(Function.call,e(70).f(Object.prototype,"__proto__").set,2),r(t,[]),n=!(t instanceof Array)
}catch(e){n=!0}return function(t,e){return i(t,e),n?t.__proto__=e:r(t,e),t}}({},!1):void 0),check:i}},{25:25,49:49,7:7,70:70}],91:[function(t,n){"use strict";
var r=t(38),e=t(67),i=t(28),o=t(117)("species");n.exports=function(t){var n=r[t];i&&n&&!n[o]&&e.f(n,o,{configurable:!0,get:function(){return this
}})}},{117:117,28:28,38:38,67:67}],92:[function(t,n){var r=t(67).f,e=t(39),i=t(117)("toStringTag");n.exports=function(t,n,o){t&&!e(t=o?t:t.prototype,i)&&r(t,i,{configurable:!0,value:n})
}},{117:117,39:39,67:67}],93:[function(t,n){var r=t(94)("keys"),e=t(114);n.exports=function(t){return r[t]||(r[t]=e(t))}},{114:114,94:94}],94:[function(t,n){var r=t(38),e="__core-js_shared__",i=r[e]||(r[e]={});
n.exports=function(t){return i[t]||(i[t]={})}},{38:38}],95:[function(t,n){var r=t(7),e=t(3),i=t(117)("species");n.exports=function(t,n){var o,u=r(t).constructor;
return void 0===u||void 0==(o=r(u)[i])?n:e(o)}},{117:117,3:3,7:7}],96:[function(t,n){var r=t(34);n.exports=function(t,n){return!!t&&r(function(){n?t.call(null,function(){},1):t.call(null)
})}},{34:34}],97:[function(t,n){var r=t(106),e=t(27);n.exports=function(t){return function(n,i){var o,u,c=String(e(n)),f=r(i),a=c.length;
return 0>f||f>=a?t?"":void 0:(o=c.charCodeAt(f),55296>o||o>56319||f+1===a||(u=c.charCodeAt(f+1))<56320||u>57343?t?c.charAt(f):o:t?c.slice(f,f+2):(o-55296<<10)+(u-56320)+65536)
}}},{106:106,27:27}],98:[function(t,n){var r=t(50),e=t(27);n.exports=function(t,n,i){if(r(n))throw TypeError("String#"+i+" doesn't accept regex!");
return String(e(t))}},{27:27,50:50}],99:[function(t,n){var r=t(32),e=t(34),i=t(27),o=/"/g,u=function(t,n,r,e){var u=String(i(t)),c="<"+n;
return""!==r&&(c+=" "+r+'="'+String(e).replace(o,"&quot;")+'"'),c+">"+u+"</"+n+">"};n.exports=function(t,n){var i={};i[t]=n(u),r(r.P+r.F*e(function(){var n=""[t]('"');
return n!==n.toLowerCase()||n.split('"').length>3}),"String",i)}},{27:27,32:32,34:34}],100:[function(t,n){var r=t(108),e=t(101),i=t(27);
n.exports=function(t,n,o,u){var c=String(i(t)),f=c.length,a=void 0===o?" ":String(o),s=r(n);if(f>=s||""==a)return c;var l=s-f,h=e.call(a,Math.ceil(l/a.length));
return h.length>l&&(h=h.slice(0,l)),u?h+c:c+h}},{101:101,108:108,27:27}],101:[function(t,n){"use strict";var r=t(106),e=t(27);
n.exports=function(t){var n=String(e(this)),i="",o=r(t);if(0>o||o==1/0)throw RangeError("Count can't be negative");for(;o>0;(o>>>=1)&&(n+=n))1&o&&(i+=n);
return i}},{106:106,27:27}],102:[function(t,n){var r=t(32),e=t(27),i=t(34),o=t(103),u="["+o+"]",c="​",f=RegExp("^"+u+u+"*"),a=RegExp(u+u+"*$"),s=function(t,n,e){var u={},f=i(function(){return!!o[t]()||c[t]()!=c
}),a=u[t]=f?n(l):o[t];e&&(u[e]=a),r(r.P+r.F*f,"String",u)},l=s.trim=function(t,n){return t=String(e(t)),1&n&&(t=t.replace(f,"")),2&n&&(t=t.replace(a,"")),t
};n.exports=s},{103:103,27:27,32:32,34:34}],103:[function(t,n){n.exports="	\n\f\r   ᠎             　\u2028\u2029﻿"},{}],104:[function(t,n){var r,e,i,o=t(25),u=t(44),c=t(41),f=t(29),a=t(38),s=a.process,l=a.setImmediate,h=a.clearImmediate,v=a.MessageChannel,p=0,g={},d="onreadystatechange",y=function(){var t=+this;
if(g.hasOwnProperty(t)){var n=g[t];delete g[t],n()}},m=function(t){y.call(t.data)};l&&h||(l=function(t){for(var n=[],e=1;arguments.length>e;)n.push(arguments[e++]);
return g[++p]=function(){u("function"==typeof t?t:Function(t),n)},r(p),p},h=function(t){delete g[t]},"process"==t(18)(s)?r=function(t){s.nextTick(o(y,t,1))
}:v?(e=new v,i=e.port2,e.port1.onmessage=m,r=o(i.postMessage,i,1)):a.addEventListener&&"function"==typeof postMessage&&!a.importScripts?(r=function(t){a.postMessage(t+"","*")
},a.addEventListener("message",m,!1)):r=d in f("script")?function(t){c.appendChild(f("script"))[d]=function(){c.removeChild(this),y.call(t)
}}:function(t){setTimeout(o(y,t,1),0)}),n.exports={set:l,clear:h}},{18:18,25:25,29:29,38:38,41:41,44:44}],105:[function(t,n){var r=t(106),e=Math.max,i=Math.min;
n.exports=function(t,n){return t=r(t),0>t?e(t+n,0):i(t,n)}},{106:106}],106:[function(t,n){var r=Math.ceil,e=Math.floor;n.exports=function(t){return isNaN(t=+t)?0:(t>0?e:r)(t)
}},{}],107:[function(t,n){var r=t(45),e=t(27);n.exports=function(t){return r(e(t))}},{27:27,45:45}],108:[function(t,n){var r=t(106),e=Math.min;
n.exports=function(t){return t>0?e(r(t),9007199254740991):0}},{106:106}],109:[function(t,n){var r=t(27);n.exports=function(t){return Object(r(t))
}},{27:27}],110:[function(t,n){var r=t(49);n.exports=function(t,n){if(!r(t))return t;var e,i;if(n&&"function"==typeof(e=t.toString)&&!r(i=e.call(t)))return i;
if("function"==typeof(e=t.valueOf)&&!r(i=e.call(t)))return i;if(!n&&"function"==typeof(e=t.toString)&&!r(i=e.call(t)))return i;
throw TypeError("Can't convert object to primitive value")}},{49:49}],111:[function(t,n){"use strict";if(t(28)){var r=t(58),e=t(38),i=t(34),o=t(32),u=t(113),c=t(112),f=t(25),a=t(6),s=t(85),l=t(40),h=t(86),v=t(106),p=t(108),g=t(105),d=t(110),y=t(39),m=t(89),b=t(17),w=t(49),x=t(109),_=t(46),S=t(66),E=t(74),O=t(72).f,F=t(118),P=t(114),M=t(117),j=t(12),A=t(11),I=t(95),N=t(130),R=t(56),k=t(54),T=t(91),L=t(9),C=t(8),U=t(67),D=t(70),W=U.f,G=D.f,B=e.RangeError,V=e.TypeError,z=e.Uint8Array,Y="ArrayBuffer",J="Shared"+Y,K="BYTES_PER_ELEMENT",q="prototype",X=Array[q],$=c.ArrayBuffer,H=c.DataView,Z=j(0),Q=j(2),tn=j(3),nn=j(4),rn=j(5),en=j(6),on=A(!0),un=A(!1),cn=N.values,fn=N.keys,an=N.entries,sn=X.lastIndexOf,ln=X.reduce,hn=X.reduceRight,vn=X.join,pn=X.sort,gn=X.slice,dn=X.toString,yn=X.toLocaleString,mn=M("iterator"),bn=M("toStringTag"),wn=P("typed_constructor"),xn=P("def_constructor"),_n=u.CONSTR,Sn=u.TYPED,En=u.VIEW,On="Wrong length!",Fn=j(1,function(t,n){return Nn(I(t,t[xn]),n)
}),Pn=i(function(){return 1===new z(new Uint16Array([1]).buffer)[0]}),Mn=!!z&&!!z[q].set&&i(function(){new z(1).set({})}),jn=function(t,n){if(void 0===t)throw V(On);
var r=+t,e=p(t);if(n&&!m(r,e))throw B(On);return e},An=function(t,n){var r=v(t);if(0>r||r%n)throw B("Wrong offset!");return r
},In=function(t){if(w(t)&&Sn in t)return t;throw V(t+" is not a typed array!")},Nn=function(t,n){if(!(w(t)&&wn in t))throw V("It is not a typed array constructor!");
return new t(n)},Rn=function(t,n){return kn(I(t,t[xn]),n)},kn=function(t,n){for(var r=0,e=n.length,i=Nn(t,e);e>r;)i[r]=n[r++];
return i},Tn=function(t,n,r){W(t,n,{get:function(){return this._d[r]}})},Ln=function(t){var n,r,e,i,o,u,c=x(t),a=arguments.length,s=a>1?arguments[1]:void 0,l=void 0!==s,h=F(c);
if(void 0!=h&&!_(h)){for(u=h.call(c),e=[],n=0;!(o=u.next()).done;n++)e.push(o.value);c=e}for(l&&a>2&&(s=f(s,arguments[2],2)),n=0,r=p(c.length),i=Nn(this,r);r>n;n++)i[n]=l?s(c[n],n):c[n];
return i},Cn=function(){for(var t=0,n=arguments.length,r=Nn(this,n);n>t;)r[t]=arguments[t++];return r},Un=!!z&&i(function(){yn.call(new z(1))
}),Dn=function(){return yn.apply(Un?gn.call(In(this)):In(this),arguments)},Wn={copyWithin:function(t,n){return C.call(In(this),t,n,arguments.length>2?arguments[2]:void 0)
},every:function(t){return nn(In(this),t,arguments.length>1?arguments[1]:void 0)},fill:function(){return L.apply(In(this),arguments)
},filter:function(t){return Rn(this,Q(In(this),t,arguments.length>1?arguments[1]:void 0))},find:function(t){return rn(In(this),t,arguments.length>1?arguments[1]:void 0)
},findIndex:function(t){return en(In(this),t,arguments.length>1?arguments[1]:void 0)},forEach:function(t){Z(In(this),t,arguments.length>1?arguments[1]:void 0)
},indexOf:function(t){return un(In(this),t,arguments.length>1?arguments[1]:void 0)},includes:function(t){return on(In(this),t,arguments.length>1?arguments[1]:void 0)
},join:function(){return vn.apply(In(this),arguments)},lastIndexOf:function(){return sn.apply(In(this),arguments)},map:function(t){return Fn(In(this),t,arguments.length>1?arguments[1]:void 0)
},reduce:function(){return ln.apply(In(this),arguments)},reduceRight:function(){return hn.apply(In(this),arguments)},reverse:function(){for(var t,n=this,r=In(n).length,e=Math.floor(r/2),i=0;e>i;)t=n[i],n[i++]=n[--r],n[r]=t;
return n},some:function(t){return tn(In(this),t,arguments.length>1?arguments[1]:void 0)},sort:function(t){return pn.call(In(this),t)
},subarray:function(t,n){var r=In(this),e=r.length,i=g(t,e);return new(I(r,r[xn]))(r.buffer,r.byteOffset+i*r.BYTES_PER_ELEMENT,p((void 0===n?e:g(n,e))-i))
}},Gn=function(t,n){return Rn(this,gn.call(In(this),t,n))},Bn=function(t){In(this);var n=An(arguments[1],1),r=this.length,e=x(t),i=p(e.length),o=0;
if(i+n>r)throw B(On);for(;i>o;)this[n+o]=e[o++]},Vn={entries:function(){return an.call(In(this))},keys:function(){return fn.call(In(this))
},values:function(){return cn.call(In(this))}},zn=function(t,n){return w(t)&&t[Sn]&&"symbol"!=typeof n&&n in t&&String(+n)==String(n)
},Yn=function(t,n){return zn(t,n=d(n,!0))?s(2,t[n]):G(t,n)},Jn=function(t,n,r){return!(zn(t,n=d(n,!0))&&w(r)&&y(r,"value"))||y(r,"get")||y(r,"set")||r.configurable||y(r,"writable")&&!r.writable||y(r,"enumerable")&&!r.enumerable?W(t,n,r):(t[n]=r.value,t)
};_n||(D.f=Yn,U.f=Jn),o(o.S+o.F*!_n,"Object",{getOwnPropertyDescriptor:Yn,defineProperty:Jn}),i(function(){dn.call({})})&&(dn=yn=function(){return vn.call(this)
});var Kn=h({},Wn);h(Kn,Vn),l(Kn,mn,Vn.values),h(Kn,{slice:Gn,set:Bn,constructor:function(){},toString:dn,toLocaleString:Dn}),Tn(Kn,"buffer","b"),Tn(Kn,"byteOffset","o"),Tn(Kn,"byteLength","l"),Tn(Kn,"length","e"),W(Kn,bn,{get:function(){return this[Sn]
}}),n.exports=function(t,n,c,f){f=!!f;var s=t+(f?"Clamped":"")+"Array",h="Uint8Array"!=s,v="get"+t,g="set"+t,d=e[s],y=d||{},m=d&&E(d),x=!d||!u.ABV,_={},F=d&&d[q],P=function(t,r){var e=t._d;
return e.v[v](r*n+e.o,Pn)},M=function(t,r,e){var i=t._d;f&&(e=(e=Math.round(e))<0?0:e>255?255:255&e),i.v[g](r*n+i.o,e,Pn)
},j=function(t,n){W(t,n,{get:function(){return P(this,n)},set:function(t){return M(this,n,t)},enumerable:!0})};x?(d=c(function(t,r,e,i){a(t,d,s,"_d");
var o,u,c,f,h=0,v=0;if(w(r)){if(!(r instanceof $||(f=b(r))==Y||f==J))return Sn in r?kn(d,r):Ln.call(d,r);o=r,v=An(e,n);var g=r.byteLength;
if(void 0===i){if(g%n)throw B(On);if(u=g-v,0>u)throw B(On)}else if(u=p(i)*n,u+v>g)throw B(On);c=u/n}else c=jn(r,!0),u=c*n,o=new $(u);
for(l(t,"_d",{b:o,o:v,l:u,e:c,v:new H(o)});c>h;)j(t,h++)}),F=d[q]=S(Kn),l(F,"constructor",d)):k(function(t){new d(null),new d(t)
},!0)||(d=c(function(t,r,e,i){a(t,d,s);var o;return w(r)?r instanceof $||(o=b(r))==Y||o==J?void 0!==i?new y(r,An(e,n),i):void 0!==e?new y(r,An(e,n)):new y(r):Sn in r?kn(d,r):Ln.call(d,r):new y(jn(r,h))
}),Z(m!==Function.prototype?O(y).concat(O(m)):O(y),function(t){t in d||l(d,t,y[t])}),d[q]=F,r||(F.constructor=d));var A=F[mn],I=!!A&&("values"==A.name||void 0==A.name),N=Vn.values;
l(d,wn,!0),l(F,Sn,s),l(F,En,!0),l(F,xn,d),(f?new d(1)[bn]==s:bn in F)||W(F,bn,{get:function(){return s}}),_[s]=d,o(o.G+o.W+o.F*(d!=y),_),o(o.S,s,{BYTES_PER_ELEMENT:n,from:Ln,of:Cn}),K in F||l(F,K,n),o(o.P,s,Wn),T(s),o(o.P+o.F*Mn,s,{set:Bn}),o(o.P+o.F*!I,s,Vn),o(o.P+o.F*(F.toString!=dn),s,{toString:dn}),o(o.P+o.F*i(function(){new d(1).slice()
}),s,{slice:Gn}),o(o.P+o.F*(i(function(){return[1,2].toLocaleString()!=new d([1,2]).toLocaleString()})||!i(function(){F.toLocaleString.call([1,2])
})),s,{toLocaleString:Dn}),R[s]=I?A:N,r||I||l(F,mn,N)}}else n.exports=function(){}},{105:105,106:106,108:108,109:109,11:11,110:110,112:112,113:113,114:114,117:117,118:118,12:12,130:130,17:17,25:25,28:28,32:32,34:34,38:38,39:39,40:40,46:46,49:49,54:54,56:56,58:58,6:6,66:66,67:67,70:70,72:72,74:74,8:8,85:85,86:86,89:89,9:9,91:91,95:95}],112:[function(t,n,r){"use strict";
var e=t(38),i=t(28),o=t(58),u=t(113),c=t(40),f=t(86),a=t(34),s=t(6),l=t(106),h=t(108),v=t(72).f,p=t(67).f,g=t(9),d=t(92),y="ArrayBuffer",m="DataView",b="prototype",w="Wrong length!",x="Wrong index!",_=e[y],S=e[m],E=e.Math,O=e.RangeError,F=e.Infinity,P=_,M=E.abs,j=E.pow,A=E.floor,I=E.log,N=E.LN2,R="buffer",k="byteLength",T="byteOffset",L=i?"_b":R,C=i?"_l":k,U=i?"_o":T,D=function(t,n,r){var e,i,o,u=Array(r),c=8*r-n-1,f=(1<<c)-1,a=f>>1,s=23===n?j(2,-24)-j(2,-77):0,l=0,h=0>t||0===t&&0>1/t?1:0;
for(t=M(t),t!=t||t===F?(i=t!=t?1:0,e=f):(e=A(I(t)/N),t*(o=j(2,-e))<1&&(e--,o*=2),t+=e+a>=1?s/o:s*j(2,1-a),t*o>=2&&(e++,o/=2),e+a>=f?(i=0,e=f):e+a>=1?(i=(t*o-1)*j(2,n),e+=a):(i=t*j(2,a-1)*j(2,n),e=0));n>=8;u[l++]=255&i,i/=256,n-=8);for(e=e<<n|i,c+=n;c>0;u[l++]=255&e,e/=256,c-=8);return u[--l]|=128*h,u
},W=function(t,n,r){var e,i=8*r-n-1,o=(1<<i)-1,u=o>>1,c=i-7,f=r-1,a=t[f--],s=127&a;for(a>>=7;c>0;s=256*s+t[f],f--,c-=8);for(e=s&(1<<-c)-1,s>>=-c,c+=n;c>0;e=256*e+t[f],f--,c-=8);if(0===s)s=1-u;
else{if(s===o)return e?0/0:a?-F:F;e+=j(2,n),s-=u}return(a?-1:1)*e*j(2,s-n)},G=function(t){return t[3]<<24|t[2]<<16|t[1]<<8|t[0]
},B=function(t){return[255&t]},V=function(t){return[255&t,t>>8&255]},z=function(t){return[255&t,t>>8&255,t>>16&255,t>>24&255]
},Y=function(t){return D(t,52,8)},J=function(t){return D(t,23,4)},K=function(t,n,r){p(t[b],n,{get:function(){return this[r]
}})},q=function(t,n,r,e){var i=+r,o=l(i);if(i!=o||0>o||o+n>t[C])throw O(x);var u=t[L]._b,c=o+t[U],f=u.slice(c,c+n);return e?f:f.reverse()
},X=function(t,n,r,e,i,o){var u=+r,c=l(u);if(u!=c||0>c||c+n>t[C])throw O(x);for(var f=t[L]._b,a=c+t[U],s=e(+i),h=0;n>h;h++)f[a+h]=s[o?h:n-h-1]
},$=function(t,n){s(t,_,y);var r=+n,e=h(r);if(r!=e)throw O(w);return e};if(u.ABV){if(!a(function(){new _})||!a(function(){new _(.5)
})){_=function(t){return new P($(this,t))};for(var H,Z=_[b]=P[b],Q=v(P),tn=0;Q.length>tn;)(H=Q[tn++])in _||c(_,H,P[H]);o||(Z.constructor=_)
}var nn=new S(new _(2)),rn=S[b].setInt8;nn.setInt8(0,2147483648),nn.setInt8(1,2147483649),!nn.getInt8(0)&&nn.getInt8(1)||f(S[b],{setInt8:function(t,n){rn.call(this,t,n<<24>>24)
},setUint8:function(t,n){rn.call(this,t,n<<24>>24)}},!0)}else _=function(t){var n=$(this,t);this._b=g.call(Array(n),0),this[C]=n
},S=function(t,n,r){s(this,S,m),s(t,_,m);var e=t[C],i=l(n);if(0>i||i>e)throw O("Wrong offset!");if(r=void 0===r?e-i:h(r),i+r>e)throw O(w);
this[L]=t,this[U]=i,this[C]=r},i&&(K(_,k,"_l"),K(S,R,"_b"),K(S,k,"_l"),K(S,T,"_o")),f(S[b],{getInt8:function(t){return q(this,1,t)[0]<<24>>24
},getUint8:function(t){return q(this,1,t)[0]},getInt16:function(t){var n=q(this,2,t,arguments[1]);return(n[1]<<8|n[0])<<16>>16
},getUint16:function(t){var n=q(this,2,t,arguments[1]);return n[1]<<8|n[0]},getInt32:function(t){return G(q(this,4,t,arguments[1]))
},getUint32:function(t){return G(q(this,4,t,arguments[1]))>>>0},getFloat32:function(t){return W(q(this,4,t,arguments[1]),23,4)
},getFloat64:function(t){return W(q(this,8,t,arguments[1]),52,8)},setInt8:function(t,n){X(this,1,t,B,n)},setUint8:function(t,n){X(this,1,t,B,n)
},setInt16:function(t,n){X(this,2,t,V,n,arguments[2])},setUint16:function(t,n){X(this,2,t,V,n,arguments[2])},setInt32:function(t,n){X(this,4,t,z,n,arguments[2])
},setUint32:function(t,n){X(this,4,t,z,n,arguments[2])},setFloat32:function(t,n){X(this,4,t,J,n,arguments[2])},setFloat64:function(t,n){X(this,8,t,Y,n,arguments[2])
}});d(_,y),d(S,m),c(S[b],u.VIEW,!0),r[y]=_,r[m]=S},{106:106,108:108,113:113,28:28,34:34,38:38,40:40,58:58,6:6,67:67,72:72,86:86,9:9,92:92}],113:[function(t,n){for(var r,e=t(38),i=t(40),o=t(114),u=o("typed_array"),c=o("view"),f=!(!e.ArrayBuffer||!e.DataView),a=f,s=0,l=9,h="Int8Array,Uint8Array,Uint8ClampedArray,Int16Array,Uint16Array,Int32Array,Uint32Array,Float32Array,Float64Array".split(",");l>s;)(r=e[h[s++]])?(i(r.prototype,u,!0),i(r.prototype,c,!0)):a=!1;
n.exports={ABV:f,CONSTR:a,TYPED:u,VIEW:c}},{114:114,38:38,40:40}],114:[function(t,n){var r=0,e=Math.random();n.exports=function(t){return"Symbol(".concat(void 0===t?"":t,")_",(++r+e).toString(36))
}},{}],115:[function(t,n){var r=t(38),e=t(23),i=t(58),o=t(116),u=t(67).f;n.exports=function(t){var n=e.Symbol||(e.Symbol=i?{}:r.Symbol||{});
"_"==t.charAt(0)||t in n||u(n,t,{value:o.f(t)})}},{116:116,23:23,38:38,58:58,67:67}],116:[function(t,n,r){r.f=t(117)},{117:117}],117:[function(t,n){var r=t(94)("wks"),e=t(114),i=t(38).Symbol,o="function"==typeof i,u=n.exports=function(t){return r[t]||(r[t]=o&&i[t]||(o?i:e)("Symbol."+t))
};u.store=r},{114:114,38:38,94:94}],118:[function(t,n){var r=t(17),e=t(117)("iterator"),i=t(56);n.exports=t(23).getIteratorMethod=function(t){return void 0!=t?t[e]||t["@@iterator"]||i[r(t)]:void 0
}},{117:117,17:17,23:23,56:56}],119:[function(t){var n=t(32),r=t(88)(/[\\^$*+?.()|[\]{}]/g,"\\$&");n(n.S,"RegExp",{escape:function(t){return r(t)
}})},{32:32,88:88}],120:[function(t){var n=t(32);n(n.P,"Array",{copyWithin:t(8)}),t(5)("copyWithin")},{32:32,5:5,8:8}],121:[function(t){"use strict";
var n=t(32),r=t(12)(4);n(n.P+n.F*!t(96)([].every,!0),"Array",{every:function(t){return r(this,t,arguments[1])}})},{12:12,32:32,96:96}],122:[function(t){var n=t(32);
n(n.P,"Array",{fill:t(9)}),t(5)("fill")},{32:32,5:5,9:9}],123:[function(t){"use strict";var n=t(32),r=t(12)(2);n(n.P+n.F*!t(96)([].filter,!0),"Array",{filter:function(t){return r(this,t,arguments[1])
}})},{12:12,32:32,96:96}],124:[function(t){"use strict";var n=t(32),r=t(12)(6),e="findIndex",i=!0;e in[]&&Array(1)[e](function(){i=!1
}),n(n.P+n.F*i,"Array",{findIndex:function(t){return r(this,t,arguments.length>1?arguments[1]:void 0)}}),t(5)(e)},{12:12,32:32,5:5}],125:[function(t){"use strict";
var n=t(32),r=t(12)(5),e="find",i=!0;e in[]&&Array(1)[e](function(){i=!1}),n(n.P+n.F*i,"Array",{find:function(t){return r(this,t,arguments.length>1?arguments[1]:void 0)
}}),t(5)(e)},{12:12,32:32,5:5}],126:[function(t){"use strict";var n=t(32),r=t(12)(0),e=t(96)([].forEach,!0);n(n.P+n.F*!e,"Array",{forEach:function(t){return r(this,t,arguments[1])
}})},{12:12,32:32,96:96}],127:[function(t){"use strict";var n=t(25),r=t(32),e=t(109),i=t(51),o=t(46),u=t(108),c=t(24),f=t(118);
r(r.S+r.F*!t(54)(function(t){Array.from(t)}),"Array",{from:function(t){var r,a,s,l,h=e(t),v="function"==typeof this?this:Array,p=arguments.length,g=p>1?arguments[1]:void 0,d=void 0!==g,y=0,m=f(h);
if(d&&(g=n(g,p>2?arguments[2]:void 0,2)),void 0==m||v==Array&&o(m))for(r=u(h.length),a=new v(r);r>y;y++)c(a,y,d?g(h[y],y):h[y]);
else for(l=m.call(h),a=new v;!(s=l.next()).done;y++)c(a,y,d?i(l,g,[s.value,y],!0):s.value);return a.length=y,a}})},{108:108,109:109,118:118,24:24,25:25,32:32,46:46,51:51,54:54}],128:[function(t){"use strict";
var n=t(32),r=t(11)(!1),e=[].indexOf,i=!!e&&1/[1].indexOf(1,-0)<0;n(n.P+n.F*(i||!t(96)(e)),"Array",{indexOf:function(t){return i?e.apply(this,arguments)||0:r(this,t,arguments[1])
}})},{11:11,32:32,96:96}],129:[function(t){var n=t(32);n(n.S,"Array",{isArray:t(47)})},{32:32,47:47}],130:[function(t,n){"use strict";
var r=t(5),e=t(55),i=t(56),o=t(107);n.exports=t(53)(Array,"Array",function(t,n){this._t=o(t),this._i=0,this._k=n},function(){var t=this._t,n=this._k,r=this._i++;
return!t||r>=t.length?(this._t=void 0,e(1)):"keys"==n?e(0,r):"values"==n?e(0,t[r]):e(0,[r,t[r]])},"values"),i.Arguments=i.Array,r("keys"),r("values"),r("entries")
},{107:107,5:5,53:53,55:55,56:56}],131:[function(t){"use strict";var n=t(32),r=t(107),e=[].join;n(n.P+n.F*(t(45)!=Object||!t(96)(e)),"Array",{join:function(t){return e.call(r(this),void 0===t?",":t)
}})},{107:107,32:32,45:45,96:96}],132:[function(t){"use strict";var n=t(32),r=t(107),e=t(106),i=t(108),o=[].lastIndexOf,u=!!o&&1/[1].lastIndexOf(1,-0)<0;
n(n.P+n.F*(u||!t(96)(o)),"Array",{lastIndexOf:function(t){if(u)return o.apply(this,arguments)||0;var n=r(this),c=i(n.length),f=c-1;
for(arguments.length>1&&(f=Math.min(f,e(arguments[1]))),0>f&&(f=c+f);f>=0;f--)if(f in n&&n[f]===t)return f||0;return-1}})
},{106:106,107:107,108:108,32:32,96:96}],133:[function(t){"use strict";var n=t(32),r=t(12)(1);n(n.P+n.F*!t(96)([].map,!0),"Array",{map:function(t){return r(this,t,arguments[1])
}})},{12:12,32:32,96:96}],134:[function(t){"use strict";var n=t(32),r=t(24);n(n.S+n.F*t(34)(function(){function t(){}return!(Array.of.call(t)instanceof t)
}),"Array",{of:function(){for(var t=0,n=arguments.length,e=new("function"==typeof this?this:Array)(n);n>t;)r(e,t,arguments[t++]);
return e.length=n,e}})},{24:24,32:32,34:34}],135:[function(t){"use strict";var n=t(32),r=t(13);n(n.P+n.F*!t(96)([].reduceRight,!0),"Array",{reduceRight:function(t){return r(this,t,arguments.length,arguments[1],!0)
}})},{13:13,32:32,96:96}],136:[function(t){"use strict";var n=t(32),r=t(13);n(n.P+n.F*!t(96)([].reduce,!0),"Array",{reduce:function(t){return r(this,t,arguments.length,arguments[1],!1)
}})},{13:13,32:32,96:96}],137:[function(t){"use strict";var n=t(32),r=t(41),e=t(18),i=t(105),o=t(108),u=[].slice;n(n.P+n.F*t(34)(function(){r&&u.call(r)
}),"Array",{slice:function(t,n){var r=o(this.length),c=e(this);if(n=void 0===n?r:n,"Array"==c)return u.call(this,t,n);for(var f=i(t,r),a=i(n,r),s=o(a-f),l=Array(s),h=0;s>h;h++)l[h]="String"==c?this.charAt(f+h):this[f+h];
return l}})},{105:105,108:108,18:18,32:32,34:34,41:41}],138:[function(t){"use strict";var n=t(32),r=t(12)(3);n(n.P+n.F*!t(96)([].some,!0),"Array",{some:function(t){return r(this,t,arguments[1])
}})},{12:12,32:32,96:96}],139:[function(t){"use strict";var n=t(32),r=t(3),e=t(109),i=t(34),o=[].sort,u=[1,2,3];n(n.P+n.F*(i(function(){u.sort(void 0)
})||!i(function(){u.sort(null)})||!t(96)(o)),"Array",{sort:function(t){return void 0===t?o.call(e(this)):o.call(e(this),r(t))
}})},{109:109,3:3,32:32,34:34,96:96}],140:[function(t){t(91)("Array")},{91:91}],141:[function(t){var n=t(32);n(n.S,"Date",{now:function(){return(new Date).getTime()
}})},{32:32}],142:[function(t){"use strict";var n=t(32),r=t(34),e=Date.prototype.getTime,i=function(t){return t>9?t:"0"+t
};n(n.P+n.F*(r(function(){return"0385-07-25T07:06:39.999Z"!=new Date(-5e13-1).toISOString()})||!r(function(){new Date(0/0).toISOString()
})),"Date",{toISOString:function(){if(!isFinite(e.call(this)))throw RangeError("Invalid time value");var t=this,n=t.getUTCFullYear(),r=t.getUTCMilliseconds(),o=0>n?"-":n>9999?"+":"";
return o+("00000"+Math.abs(n)).slice(o?-6:-4)+"-"+i(t.getUTCMonth()+1)+"-"+i(t.getUTCDate())+"T"+i(t.getUTCHours())+":"+i(t.getUTCMinutes())+":"+i(t.getUTCSeconds())+"."+(r>99?r:"0"+i(r))+"Z"
}})},{32:32,34:34}],143:[function(t){"use strict";var n=t(32),r=t(109),e=t(110);n(n.P+n.F*t(34)(function(){return null!==new Date(0/0).toJSON()||1!==Date.prototype.toJSON.call({toISOString:function(){return 1
}})}),"Date",{toJSON:function(){var t=r(this),n=e(t);return"number"!=typeof n||isFinite(n)?t.toISOString():null}})},{109:109,110:110,32:32,34:34}],144:[function(t){var n=t(117)("toPrimitive"),r=Date.prototype;
n in r||t(40)(r,n,t(26))},{117:117,26:26,40:40}],145:[function(t){var n=Date.prototype,r="Invalid Date",e="toString",i=n[e],o=n.getTime;
new Date(0/0)+""!=r&&t(87)(n,e,function(){var t=o.call(this);return t===t?i.call(this):r})},{87:87}],146:[function(t){var n=t(32);
n(n.P,"Function",{bind:t(16)})},{16:16,32:32}],147:[function(t){"use strict";var n=t(49),r=t(74),e=t(117)("hasInstance"),i=Function.prototype;
e in i||t(67).f(i,e,{value:function(t){if("function"!=typeof this||!n(t))return!1;if(!n(this.prototype))return t instanceof this;
for(;t=r(t);)if(this.prototype===t)return!0;return!1}})},{117:117,49:49,67:67,74:74}],148:[function(t){var n=t(67).f,r=t(85),e=t(39),i=Function.prototype,o=/^\s*function ([^ (]*)/,u="name",c=Object.isExtensible||function(){return!0
};u in i||t(28)&&n(i,u,{configurable:!0,get:function(){try{var t=this,i=(""+t).match(o)[1];return e(t,u)||!c(t)||n(t,u,r(5,i)),i
}catch(t){return""}}})},{28:28,39:39,67:67,85:85}],149:[function(t,n){"use strict";var r=t(19);n.exports=t(22)("Map",function(t){return function(){return t(this,arguments.length>0?arguments[0]:void 0)
}},{get:function(t){var n=r.getEntry(this,t);return n&&n.v},set:function(t,n){return r.def(this,0===t?0:t,n)}},r,!0)},{19:19,22:22}],150:[function(t){var n=t(32),r=t(60),e=Math.sqrt,i=Math.acosh;
n(n.S+n.F*!(i&&710==Math.floor(i(Number.MAX_VALUE))&&i(1/0)==1/0),"Math",{acosh:function(t){return(t=+t)<1?0/0:t>94906265.62425156?Math.log(t)+Math.LN2:r(t-1+e(t-1)*e(t+1))
}})},{32:32,60:60}],151:[function(t){function n(t){return isFinite(t=+t)&&0!=t?0>t?-n(-t):Math.log(t+Math.sqrt(t*t+1)):t}var r=t(32),e=Math.asinh;
r(r.S+r.F*!(e&&1/e(0)>0),"Math",{asinh:n})},{32:32}],152:[function(t){var n=t(32),r=Math.atanh;n(n.S+n.F*!(r&&1/r(-0)<0),"Math",{atanh:function(t){return 0==(t=+t)?t:Math.log((1+t)/(1-t))/2
}})},{32:32}],153:[function(t){var n=t(32),r=t(61);n(n.S,"Math",{cbrt:function(t){return r(t=+t)*Math.pow(Math.abs(t),1/3)
}})},{32:32,61:61}],154:[function(t){var n=t(32);n(n.S,"Math",{clz32:function(t){return(t>>>=0)?31-Math.floor(Math.log(t+.5)*Math.LOG2E):32
}})},{32:32}],155:[function(t){var n=t(32),r=Math.exp;n(n.S,"Math",{cosh:function(t){return(r(t=+t)+r(-t))/2}})},{32:32}],156:[function(t){var n=t(32),r=t(59);
n(n.S+n.F*(r!=Math.expm1),"Math",{expm1:r})},{32:32,59:59}],157:[function(t){var n=t(32),r=t(61),e=Math.pow,i=e(2,-52),o=e(2,-23),u=e(2,127)*(2-o),c=e(2,-126),f=function(t){return t+1/i-1/i
};n(n.S,"Math",{fround:function(t){var n,e,a=Math.abs(t),s=r(t);return c>a?s*f(a/c/o)*c*o:(n=(1+o/i)*a,e=n-(n-a),e>u||e!=e?s*(1/0):s*e)
}})},{32:32,61:61}],158:[function(t){var n=t(32),r=Math.abs;n(n.S,"Math",{hypot:function(){for(var t,n,e=0,i=0,o=arguments.length,u=0;o>i;)t=r(arguments[i++]),t>u?(n=u/t,e=e*n*n+1,u=t):t>0?(n=t/u,e+=n*n):e+=t;
return u===1/0?1/0:u*Math.sqrt(e)}})},{32:32}],159:[function(t){var n=t(32),r=Math.imul;n(n.S+n.F*t(34)(function(){return-5!=r(4294967295,5)||2!=r.length
}),"Math",{imul:function(t,n){var r=65535,e=+t,i=+n,o=r&e,u=r&i;return 0|o*u+((r&e>>>16)*u+o*(r&i>>>16)<<16>>>0)}})},{32:32,34:34}],160:[function(t){var n=t(32);
n(n.S,"Math",{log10:function(t){return Math.log(t)/Math.LN10}})},{32:32}],161:[function(t){var n=t(32);n(n.S,"Math",{log1p:t(60)})
},{32:32,60:60}],162:[function(t){var n=t(32);n(n.S,"Math",{log2:function(t){return Math.log(t)/Math.LN2}})},{32:32}],163:[function(t){var n=t(32);
n(n.S,"Math",{sign:t(61)})},{32:32,61:61}],164:[function(t){var n=t(32),r=t(59),e=Math.exp;n(n.S+n.F*t(34)(function(){return-2e-17!=!Math.sinh(-2e-17)
}),"Math",{sinh:function(t){return Math.abs(t=+t)<1?(r(t)-r(-t))/2:(e(t-1)-e(-t-1))*(Math.E/2)}})},{32:32,34:34,59:59}],165:[function(t){var n=t(32),r=t(59),e=Math.exp;
n(n.S,"Math",{tanh:function(t){var n=r(t=+t),i=r(-t);return n==1/0?1:i==1/0?-1:(n-i)/(e(t)+e(-t))}})},{32:32,59:59}],166:[function(t){var n=t(32);
n(n.S,"Math",{trunc:function(t){return(t>0?Math.floor:Math.ceil)(t)}})},{32:32}],167:[function(t){"use strict";var n=t(38),r=t(39),e=t(18),i=t(43),o=t(110),u=t(34),c=t(72).f,f=t(70).f,a=t(67).f,s=t(102).trim,l="Number",h=n[l],v=h,p=h.prototype,g=e(t(66)(p))==l,d="trim"in String.prototype,y=function(t){var n=o(t,!1);
if("string"==typeof n&&n.length>2){n=d?n.trim():s(n,3);var r,e,i,u=n.charCodeAt(0);if(43===u||45===u){if(r=n.charCodeAt(2),88===r||120===r)return 0/0
}else if(48===u){switch(n.charCodeAt(1)){case 66:case 98:e=2,i=49;break;case 79:case 111:e=8,i=55;break;default:return+n}for(var c,f=n.slice(2),a=0,l=f.length;l>a;a++)if(c=f.charCodeAt(a),48>c||c>i)return 0/0;
return parseInt(f,e)}}return+n};if(!h(" 0o1")||!h("0b1")||h("+0x1")){h=function(t){var n=arguments.length<1?0:t,r=this;return r instanceof h&&(g?u(function(){p.valueOf.call(r)
}):e(r)!=l)?i(new v(y(n)),r,h):y(n)};for(var m,b=t(28)?c(v):"MAX_VALUE,MIN_VALUE,NaN,NEGATIVE_INFINITY,POSITIVE_INFINITY,EPSILON,isFinite,isInteger,isNaN,isSafeInteger,MAX_SAFE_INTEGER,MIN_SAFE_INTEGER,parseFloat,parseInt,isInteger".split(","),w=0;b.length>w;w++)r(v,m=b[w])&&!r(h,m)&&a(h,m,f(v,m));
h.prototype=p,p.constructor=h,t(87)(n,l,h)}},{102:102,110:110,18:18,28:28,34:34,38:38,39:39,43:43,66:66,67:67,70:70,72:72,87:87}],168:[function(t){var n=t(32);
n(n.S,"Number",{EPSILON:Math.pow(2,-52)})},{32:32}],169:[function(t){var n=t(32),r=t(38).isFinite;n(n.S,"Number",{isFinite:function(t){return"number"==typeof t&&r(t)
}})},{32:32,38:38}],170:[function(t){var n=t(32);n(n.S,"Number",{isInteger:t(48)})},{32:32,48:48}],171:[function(t){var n=t(32);
n(n.S,"Number",{isNaN:function(t){return t!=t}})},{32:32}],172:[function(t){var n=t(32),r=t(48),e=Math.abs;n(n.S,"Number",{isSafeInteger:function(t){return r(t)&&e(t)<=9007199254740991
}})},{32:32,48:48}],173:[function(t){var n=t(32);n(n.S,"Number",{MAX_SAFE_INTEGER:9007199254740991})},{32:32}],174:[function(t){var n=t(32);
n(n.S,"Number",{MIN_SAFE_INTEGER:-9007199254740991})},{32:32}],175:[function(t){var n=t(32),r=t(81);n(n.S+n.F*(Number.parseFloat!=r),"Number",{parseFloat:r})
},{32:32,81:81}],176:[function(t){var n=t(32),r=t(82);n(n.S+n.F*(Number.parseInt!=r),"Number",{parseInt:r})},{32:32,82:82}],177:[function(t){"use strict";
var n=t(32),r=t(106),e=t(4),i=t(101),o=1..toFixed,u=Math.floor,c=[0,0,0,0,0,0],f="Number.toFixed: incorrect invocation!",a="0",s=function(t,n){for(var r=-1,e=n;++r<6;)e+=t*c[r],c[r]=e%1e7,e=u(e/1e7)
},l=function(t){for(var n=6,r=0;--n>=0;)r+=c[n],c[n]=u(r/t),r=r%t*1e7},h=function(){for(var t=6,n="";--t>=0;)if(""!==n||0===t||0!==c[t]){var r=String(c[t]);
n=""===n?r:n+i.call(a,7-r.length)+r}return n},v=function(t,n,r){return 0===n?r:n%2===1?v(t,n-1,r*t):v(t*t,n/2,r)},p=function(t){for(var n=0,r=t;r>=4096;)n+=12,r/=4096;
for(;r>=2;)n+=1,r/=2;return n};n(n.P+n.F*(!!o&&("0.000"!==8e-5.toFixed(3)||"1"!==.9.toFixed(0)||"1.25"!==1.255.toFixed(2)||"1000000000000000128"!==0xde0b6b3a7640080.toFixed(0))||!t(34)(function(){o.call({})
})),"Number",{toFixed:function(t){var n,o,u,c,g=e(this,f),d=r(t),y="",m=a;if(0>d||d>20)throw RangeError(f);if(g!=g)return"NaN";
if(-1e21>=g||g>=1e21)return String(g);if(0>g&&(y="-",g=-g),g>1e-21)if(n=p(g*v(2,69,1))-69,o=0>n?g*v(2,-n,1):g/v(2,n,1),o*=4503599627370496,n=52-n,n>0){for(s(0,o),u=d;u>=7;)s(1e7,0),u-=7;
for(s(v(10,u,1),0),u=n-1;u>=23;)l(1<<23),u-=23;l(1<<u),s(1,1),l(2),m=h()}else s(0,o),s(1<<-n,0),m=h()+i.call(a,d);return d>0?(c=m.length,m=y+(d>=c?"0."+i.call(a,d-c)+m:m.slice(0,c-d)+"."+m.slice(c-d))):m=y+m,m
}})},{101:101,106:106,32:32,34:34,4:4}],178:[function(t){"use strict";var n=t(32),r=t(34),e=t(4),i=1..toPrecision;n(n.P+n.F*(r(function(){return"1"!==i.call(1,void 0)
})||!r(function(){i.call({})})),"Number",{toPrecision:function(t){var n=e(this,"Number#toPrecision: incorrect invocation!");
return void 0===t?i.call(n):i.call(n,t)}})},{32:32,34:34,4:4}],179:[function(t){var n=t(32);n(n.S+n.F,"Object",{assign:t(65)})
},{32:32,65:65}],180:[function(t){var n=t(32);n(n.S,"Object",{create:t(66)})},{32:32,66:66}],181:[function(t){var n=t(32);
n(n.S+n.F*!t(28),"Object",{defineProperties:t(68)})},{28:28,32:32,68:68}],182:[function(t){var n=t(32);n(n.S+n.F*!t(28),"Object",{defineProperty:t(67).f})
},{28:28,32:32,67:67}],183:[function(t){var n=t(49),r=t(62).onFreeze;t(78)("freeze",function(t){return function(e){return t&&n(e)?t(r(e)):e
}})},{49:49,62:62,78:78}],184:[function(t){var n=t(107),r=t(70).f;t(78)("getOwnPropertyDescriptor",function(){return function(t,e){return r(n(t),e)
}})},{107:107,70:70,78:78}],185:[function(t){t(78)("getOwnPropertyNames",function(){return t(71).f})},{71:71,78:78}],186:[function(t){var n=t(109),r=t(74);
t(78)("getPrototypeOf",function(){return function(t){return r(n(t))}})},{109:109,74:74,78:78}],187:[function(t){var n=t(49);
t(78)("isExtensible",function(t){return function(r){return!!n(r)&&(!t||t(r))}})},{49:49,78:78}],188:[function(t){var n=t(49);
t(78)("isFrozen",function(t){return function(r){return!n(r)||!!t&&t(r)}})},{49:49,78:78}],189:[function(t){var n=t(49);t(78)("isSealed",function(t){return function(r){return!n(r)||!!t&&t(r)
}})},{49:49,78:78}],190:[function(t){var n=t(32);n(n.S,"Object",{is:t(89)})},{32:32,89:89}],191:[function(t){var n=t(109),r=t(76);
t(78)("keys",function(){return function(t){return r(n(t))}})},{109:109,76:76,78:78}],192:[function(t){var n=t(49),r=t(62).onFreeze;
t(78)("preventExtensions",function(t){return function(e){return t&&n(e)?t(r(e)):e}})},{49:49,62:62,78:78}],193:[function(t){var n=t(49),r=t(62).onFreeze;
t(78)("seal",function(t){return function(e){return t&&n(e)?t(r(e)):e}})},{49:49,62:62,78:78}],194:[function(t){var n=t(32);
n(n.S,"Object",{setPrototypeOf:t(90).set})},{32:32,90:90}],195:[function(t){"use strict";var n=t(17),r={};r[t(117)("toStringTag")]="z",r+""!="[object z]"&&t(87)(Object.prototype,"toString",function(){return"[object "+n(this)+"]"
},!0)},{117:117,17:17,87:87}],196:[function(t){var n=t(32),r=t(81);n(n.G+n.F*(parseFloat!=r),{parseFloat:r})},{32:32,81:81}],197:[function(t){var n=t(32),r=t(82);
n(n.G+n.F*(parseInt!=r),{parseInt:r})},{32:32,82:82}],198:[function(t){"use strict";var n,r,e,i=t(58),o=t(38),u=t(25),c=t(17),f=t(32),a=t(49),s=t(3),l=t(6),h=t(37),v=t(95),p=t(104).set,g=t(64)(),d="Promise",y=o.TypeError,m=o.process,b=o[d],m=o.process,w="process"==c(m),x=function(){},_=!!function(){try{var t=b.resolve(1),n=(t.constructor={})[r(117)("species")]=function(t){t(x,x)
};return(w||"function"==typeof PromiseRejectionEvent)&&t.then(x)instanceof n}catch(r){}}(),S=function(t,n){return t===n||t===b&&n===e
},E=function(t){var n;return!(!a(t)||"function"!=typeof(n=t.then))&&n},O=function(t){return S(b,t)?new F(t):new r(t)},F=r=function(t){var n,r;
this.promise=new t(function(t,e){if(void 0!==n||void 0!==r)throw y("Bad Promise constructor");n=t,r=e}),this.resolve=s(n),this.reject=s(r)
},P=function(t){try{t()}catch(t){return{error:t}}},M=function(t,n){if(!t._n){t._n=!0;var r=t._c;g(function(){for(var e=t._v,i=1==t._s,o=0,u=function(t){var n,r,o=i?t.ok:t.fail,u=t.resolve,c=t.reject,f=t.domain;
try{o?(i||(2==a._h&&I(a),a._h=1),o===!0?n=e:(f&&f.enter(),n=o(e),f&&f.exit()),n===t.promise?c(y("Promise-chain cycle")):(r=E(n))?r.call(n,u,c):u(n)):c(e)
}catch(a){c(a)}};r.length>o;)u(r[o++]);t._c=[],t._n=!1,n&&!t._h&&j(t)})}},j=function(t){p.call(o,function(){var n,r,e,i=t._v;
if(A(t)&&(n=P(function(){w?m.emit("unhandledRejection",i,t):(r=o.onunhandledrejection)?r({promise:t,reason:i}):(e=o.console)&&e.error&&e.error("Unhandled promise rejection",i)
}),t._h=w||A(t)?2:1),t._a=void 0,n)throw n.error})},A=function(t){if(1==t._h)return!1;for(var n,r=t._a||t._c,e=0;r.length>e;)if(n=r[e++],n.fail||!A(n.promise))return!1;
return!0},I=function(t){p.call(o,function(){var n;w?m.emit("rejectionHandled",t):(n=o.onrejectionhandled)&&n({promise:t,reason:t._v})
})},N=function(t){var n=this;n._d||(n._d=!0,n=n._w||n,n._v=t,n._s=2,n._a||(n._a=n._c.slice()),M(n,!0))},R=function(t){var n,r=this;
if(!r._d){r._d=!0,r=r._w||r;try{if(r===t)throw y("Promise can't be resolved itself");(n=E(t))?g(function(){var t={_w:r,_d:!1};
try{n.call(e,u(R,t,1),u(N,t,1))}catch(e){N.call(t,e)}}):(r._v=t,r._s=1,M(r,!1))}catch(t){N.call({_w:r,_d:!1},t)}}};_||(b=function(t){l(this,b,d,"_h"),s(t),n.call(this);
try{t(u(R,this,1),u(N,this,1))}catch(t){N.call(this,t)}},n=function(){this._c=[],this._a=void 0,this._s=0,this._d=!1,this._v=void 0,this._h=0,this._n=!1
},n.prototype=t(86)(b.prototype,{then:function(t,n){var r=O(v(this,b));return r.ok="function"!=typeof t||t,r.fail="function"==typeof n&&n,r.domain=w?m.domain:void 0,this._c.push(r),this._a&&this._a.push(r),this._s&&M(this,!1),r.promise
},"catch":function(t){return this.then(void 0,t)}}),F=function(){var t=new n;this.promise=t,this.resolve=u(R,t,1),this.reject=u(N,t,1)
}),f(f.G+f.W+f.F*!_,{Promise:b}),t(92)(b,d),t(91)(d),e=t(23)[d],f(f.S+f.F*!_,d,{reject:function(t){var n=O(this),r=n.reject;
return r(t),n.promise}}),f(f.S+f.F*(i||!_),d,{resolve:function(t){if(t instanceof b&&S(t.constructor,this))return t;var n=O(this),r=n.resolve;
return r(t),n.promise}}),f(f.S+f.F*!(_&&t(54)(function(t){b.all(t).catch(x)})),d,{all:function(t){var n=this,r=O(n),e=r.resolve,i=r.reject,o=P(function(){var r=[],o=0,u=1;
h(t,!1,function(t){var c=o++,f=!1;r.push(void 0),u++,n.resolve(t).then(function(t){f||(f=!0,r[c]=t,--u||e(r))},i)}),--u||e(r)
});return o&&i(o.error),r.promise},race:function(t){var n=this,r=O(n),e=r.reject,i=P(function(){h(t,!1,function(t){n.resolve(t).then(r.resolve,e)
})});return i&&e(i.error),r.promise}})},{104:104,117:117,17:17,23:23,25:25,3:3,32:32,37:37,38:38,49:49,54:54,58:58,6:6,64:64,86:86,91:91,92:92,95:95}],199:[function(t){var n=t(32),r=t(3),e=t(7),i=(t(38).Reflect||{}).apply,o=Function.apply;
n(n.S+n.F*!t(34)(function(){i(function(){})}),"Reflect",{apply:function(t,n,u){var c=r(t),f=e(u);return i?i(c,n,f):o.call(c,n,f)
}})},{3:3,32:32,34:34,38:38,7:7}],200:[function(t){var n=t(32),r=t(66),e=t(3),i=t(7),o=t(49),u=t(34),c=t(16),f=(t(38).Reflect||{}).construct,a=u(function(){function t(){}return!(f(function(){},[],t)instanceof t)
}),s=!u(function(){f(function(){})});n(n.S+n.F*(a||s),"Reflect",{construct:function(t,n){e(t),i(n);var u=arguments.length<3?t:e(arguments[2]);
if(s&&!a)return f(t,n,u);if(t==u){switch(n.length){case 0:return new t;case 1:return new t(n[0]);case 2:return new t(n[0],n[1]);
case 3:return new t(n[0],n[1],n[2]);case 4:return new t(n[0],n[1],n[2],n[3])}var l=[null];return l.push.apply(l,n),new(c.apply(t,l))
}var h=u.prototype,v=r(o(h)?h:Object.prototype),p=Function.apply.call(t,v,n);return o(p)?p:v}})},{16:16,3:3,32:32,34:34,38:38,49:49,66:66,7:7}],201:[function(t){var n=t(67),r=t(32),e=t(7),i=t(110);
r(r.S+r.F*t(34)(function(){Reflect.defineProperty(n.f({},1,{value:1}),1,{value:2})}),"Reflect",{defineProperty:function(t,r,o){e(t),r=i(r,!0),e(o);
try{return n.f(t,r,o),!0}catch(t){return!1}}})},{110:110,32:32,34:34,67:67,7:7}],202:[function(t){var n=t(32),r=t(70).f,e=t(7);
n(n.S,"Reflect",{deleteProperty:function(t,n){var i=r(e(t),n);return!(i&&!i.configurable)&&delete t[n]}})},{32:32,7:7,70:70}],203:[function(t){"use strict";
var n=t(32),r=t(7),e=function(t){this._t=r(t),this._i=0;var n,e=this._k=[];for(n in t)e.push(n)};t(52)(e,"Object",function(){var t,n=this,r=n._k;
do if(n._i>=r.length)return{value:void 0,done:!0};while(!((t=r[n._i++])in n._t));return{value:t,done:!1}}),n(n.S,"Reflect",{enumerate:function(t){return new e(t)
}})},{32:32,52:52,7:7}],204:[function(t){var n=t(70),r=t(32),e=t(7);r(r.S,"Reflect",{getOwnPropertyDescriptor:function(t,r){return n.f(e(t),r)
}})},{32:32,7:7,70:70}],205:[function(t){var n=t(32),r=t(74),e=t(7);n(n.S,"Reflect",{getPrototypeOf:function(t){return r(e(t))
}})},{32:32,7:7,74:74}],206:[function(t){function n(t,o){var f,a,s=arguments.length<3?t:arguments[2];return c(t)===s?t[o]:(f=r.f(t,o))?i(f,"value")?f.value:void 0!==f.get?f.get.call(s):void 0:u(a=e(t))?n(a,o,s):void 0
}var r=t(70),e=t(74),i=t(39),o=t(32),u=t(49),c=t(7);o(o.S,"Reflect",{get:n})},{32:32,39:39,49:49,7:7,70:70,74:74}],207:[function(t){var n=t(32);
n(n.S,"Reflect",{has:function(t,n){return n in t}})},{32:32}],208:[function(t){var n=t(32),r=t(7),e=Object.isExtensible;n(n.S,"Reflect",{isExtensible:function(t){return r(t),!e||e(t)
}})},{32:32,7:7}],209:[function(t){var n=t(32);n(n.S,"Reflect",{ownKeys:t(80)})},{32:32,80:80}],210:[function(t){var n=t(32),r=t(7),e=Object.preventExtensions;
n(n.S,"Reflect",{preventExtensions:function(t){r(t);try{return e&&e(t),!0}catch(t){return!1}}})},{32:32,7:7}],211:[function(t){var n=t(32),r=t(90);
r&&n(n.S,"Reflect",{setPrototypeOf:function(t,n){r.check(t,n);try{return r.set(t,n),!0}catch(t){return!1}}})},{32:32,90:90}],212:[function(t){function n(t,u,s){var l,h,v=arguments.length<4?t:arguments[3],p=e.f(f(t),u);
if(!p){if(a(h=i(t)))return n(h,u,s,v);p=c(0)}return o(p,"value")?!(p.writable===!1||!a(v)||(l=e.f(v,u)||c(0),l.value=s,r.f(v,u,l),0)):void 0!==p.set&&(p.set.call(v,s),!0)
}var r=t(67),e=t(70),i=t(74),o=t(39),u=t(32),c=t(85),f=t(7),a=t(49);u(u.S,"Reflect",{set:n})},{32:32,39:39,49:49,67:67,7:7,70:70,74:74,85:85}],213:[function(t){var n=t(38),r=t(43),e=t(67).f,i=t(72).f,o=t(50),u=t(36),c=n.RegExp,f=c,a=c.prototype,s=/a/g,l=/a/g,h=new c(s)!==s;
if(t(28)&&(!h||t(34)(function(){return l[t(117)("match")]=!1,c(s)!=s||c(l)==l||"/a/i"!=c(s,"i")}))){c=function(t,n){var e=this instanceof c,i=o(t),s=void 0===n;
return!e&&i&&t.constructor===c&&s?t:r(h?new f(i&&!s?t.source:t,n):f((i=t instanceof c)?t.source:t,i&&s?u.call(t):n),e?this:a,c)
};for(var v=(function(t){t in c||e(c,t,{configurable:!0,get:function(){return f[t]},set:function(n){f[t]=n}})}),p=i(f),g=0;p.length>g;)v(p[g++]);
a.constructor=c,c.prototype=a,t(87)(n,"RegExp",c)}t(91)("RegExp")},{117:117,28:28,34:34,36:36,38:38,43:43,50:50,67:67,72:72,87:87,91:91}],214:[function(t){t(28)&&"g"!=/./g.flags&&t(67).f(RegExp.prototype,"flags",{configurable:!0,get:t(36)})
},{28:28,36:36,67:67}],215:[function(t){t(35)("match",1,function(t,n,r){return[function(r){"use strict";var e=t(this),i=void 0==r?void 0:r[n];
return void 0!==i?i.call(r,e):new RegExp(r)[n](String(e))},r]})},{35:35}],216:[function(t){t(35)("replace",2,function(t,n,r){return[function(e,i){"use strict";
var o=t(this),u=void 0==e?void 0:e[n];return void 0!==u?u.call(e,o,i):r.call(String(o),e,i)},r]})},{35:35}],217:[function(t){t(35)("search",1,function(t,n,r){return[function(r){"use strict";
var e=t(this),i=void 0==r?void 0:r[n];return void 0!==i?i.call(r,e):new RegExp(r)[n](String(e))},r]})},{35:35}],218:[function(t){t(35)("split",2,function(n,r,e){"use strict";
var i=t(50),o=e,u=[].push,c="split",f="length",a="lastIndex";if("c"=="abbc"[c](/(b)*/)[1]||4!="test"[c](/(?:)/,-1)[f]||2!="ab"[c](/(?:ab)*/)[f]||4!="."[c](/(.?)(.?)/)[f]||"."[c](/()()/)[f]>1||""[c](/.?/)[f]){var s=void 0===/()??/.exec("")[1];
e=function(t,n){var r=String(this);if(void 0===t&&0===n)return[];if(!i(t))return o.call(r,t,n);var e,c,l,h,v,p=[],g=(t.ignoreCase?"i":"")+(t.multiline?"m":"")+(t.unicode?"u":"")+(t.sticky?"y":""),d=0,y=void 0===n?4294967295:n>>>0,m=new RegExp(t.source,g+"g");
for(s||(e=new RegExp("^"+m.source+"$(?!\\s)",g));(c=m.exec(r))&&(l=c.index+c[0][f],!(l>d&&(p.push(r.slice(d,c.index)),!s&&c[f]>1&&c[0].replace(e,function(){for(v=1;v<arguments[f]-2;v++)void 0===arguments[v]&&(c[v]=void 0)
}),c[f]>1&&c.index<r[f]&&u.apply(p,c.slice(1)),h=c[0][f],d=l,p[f]>=y)));)m[a]===c.index&&m[a]++;return d===r[f]?!h&&m.test("")||p.push(""):p.push(r.slice(d)),p[f]>y?p.slice(0,y):p
}}else"0"[c](void 0,0)[f]&&(e=function(t,n){return void 0===t&&0===n?[]:o.call(this,t,n)});return[function(t,i){var o=n(this),u=void 0==t?void 0:t[r];
return void 0!==u?u.call(t,o,i):e.call(String(o),t,i)},e]})},{35:35,50:50}],219:[function(t){"use strict";t(214);var n=t(7),r=t(36),e=t(28),i="toString",o=/./[i],u=function(n){t(87)(RegExp.prototype,i,n,!0)
};t(34)(function(){return"/a/b"!=o.call({source:"a",flags:"b"})})?u(function(){var t=n(this);return"/".concat(t.source,"/","flags"in t?t.flags:!e&&t instanceof RegExp?r.call(t):void 0)
}):o.name!=i&&u(function(){return o.call(this)})},{214:214,28:28,34:34,36:36,7:7,87:87}],220:[function(t,n){"use strict";
var r=t(19);n.exports=t(22)("Set",function(t){return function(){return t(this,arguments.length>0?arguments[0]:void 0)}},{add:function(t){return r.def(this,t=0===t?0:t,t)
}},r)},{19:19,22:22}],221:[function(t){"use strict";t(99)("anchor",function(t){return function(n){return t(this,"a","name",n)
}})},{99:99}],222:[function(t){"use strict";t(99)("big",function(t){return function(){return t(this,"big","","")}})},{99:99}],223:[function(t){"use strict";
t(99)("blink",function(t){return function(){return t(this,"blink","","")}})},{99:99}],224:[function(t){"use strict";t(99)("bold",function(t){return function(){return t(this,"b","","")
}})},{99:99}],225:[function(t){"use strict";var n=t(32),r=t(97)(!1);n(n.P,"String",{codePointAt:function(t){return r(this,t)
}})},{32:32,97:97}],226:[function(t){"use strict";var n=t(32),r=t(108),e=t(98),i="endsWith",o=""[i];n(n.P+n.F*t(33)(i),"String",{endsWith:function(t){var n=e(this,t,i),u=arguments.length>1?arguments[1]:void 0,c=r(n.length),f=void 0===u?c:Math.min(r(u),c),a=String(t);
return o?o.call(n,a,f):n.slice(f-a.length,f)===a}})},{108:108,32:32,33:33,98:98}],227:[function(t){"use strict";t(99)("fixed",function(t){return function(){return t(this,"tt","","")
}})},{99:99}],228:[function(t){"use strict";t(99)("fontcolor",function(t){return function(n){return t(this,"font","color",n)
}})},{99:99}],229:[function(t){"use strict";t(99)("fontsize",function(t){return function(n){return t(this,"font","size",n)
}})},{99:99}],230:[function(t){var n=t(32),r=t(105),e=String.fromCharCode,i=String.fromCodePoint;n(n.S+n.F*(!!i&&1!=i.length),"String",{fromCodePoint:function(){for(var t,n=[],i=arguments.length,o=0;i>o;){if(t=+arguments[o++],r(t,1114111)!==t)throw RangeError(t+" is not a valid code point");
n.push(65536>t?e(t):e(((t-=65536)>>10)+55296,t%1024+56320))}return n.join("")}})},{105:105,32:32}],231:[function(t){"use strict";
var n=t(32),r=t(98),e="includes";n(n.P+n.F*t(33)(e),"String",{includes:function(t){return!!~r(this,t,e).indexOf(t,arguments.length>1?arguments[1]:void 0)
}})},{32:32,33:33,98:98}],232:[function(t){"use strict";t(99)("italics",function(t){return function(){return t(this,"i","","")
}})},{99:99}],233:[function(t){"use strict";var n=t(97)(!0);t(53)(String,"String",function(t){this._t=String(t),this._i=0
},function(){var t,r=this._t,e=this._i;return e>=r.length?{value:void 0,done:!0}:(t=n(r,e),this._i+=t.length,{value:t,done:!1})
})},{53:53,97:97}],234:[function(t){"use strict";t(99)("link",function(t){return function(n){return t(this,"a","href",n)}
})},{99:99}],235:[function(t){var n=t(32),r=t(107),e=t(108);n(n.S,"String",{raw:function(t){for(var n=r(t.raw),i=e(n.length),o=arguments.length,u=[],c=0;i>c;)u.push(String(n[c++])),o>c&&u.push(String(arguments[c]));
return u.join("")}})},{107:107,108:108,32:32}],236:[function(t){var n=t(32);n(n.P,"String",{repeat:t(101)})},{101:101,32:32}],237:[function(t){"use strict";
t(99)("small",function(t){return function(){return t(this,"small","","")}})},{99:99}],238:[function(t){"use strict";var n=t(32),r=t(108),e=t(98),i="startsWith",o=""[i];
n(n.P+n.F*t(33)(i),"String",{startsWith:function(t){var n=e(this,t,i),u=r(Math.min(arguments.length>1?arguments[1]:void 0,n.length)),c=String(t);
return o?o.call(n,c,u):n.slice(u,u+c.length)===c}})},{108:108,32:32,33:33,98:98}],239:[function(t){"use strict";t(99)("strike",function(t){return function(){return t(this,"strike","","")
}})},{99:99}],240:[function(t){"use strict";t(99)("sub",function(t){return function(){return t(this,"sub","","")}})},{99:99}],241:[function(t){"use strict";
t(99)("sup",function(t){return function(){return t(this,"sup","","")}})},{99:99}],242:[function(t){"use strict";t(102)("trim",function(t){return function(){return t(this,3)
}})},{102:102}],243:[function(t){"use strict";var n=t(38),r=t(39),e=t(28),i=t(32),o=t(87),u=t(62).KEY,c=t(34),f=t(94),a=t(92),s=t(114),l=t(117),h=t(116),v=t(115),p=t(57),g=t(31),d=t(47),y=t(7),m=t(107),b=t(110),w=t(85),x=t(66),_=t(71),S=t(70),E=t(67),O=t(76),F=S.f,P=E.f,M=_.f,j=n.Symbol,A=n.JSON,I=A&&A.stringify,N="prototype",R=l("_hidden"),k=l("toPrimitive"),T={}.propertyIsEnumerable,L=f("symbol-registry"),C=f("symbols"),U=f("op-symbols"),D=Object[N],W="function"==typeof j,G=n.QObject,B=!G||!G[N]||!G[N].findChild,V=e&&c(function(){return 7!=x(P({},"a",{get:function(){return P(this,"a",{value:7}).a
}})).a})?function(t,n,r){var e=F(D,n);e&&delete D[n],P(t,n,r),e&&t!==D&&P(D,n,e)}:P,z=function(t){var n=C[t]=x(j[N]);return n._k=t,n
},Y=W&&"symbol"==typeof j.iterator?function(t){return"symbol"==typeof t}:function(t){return t instanceof j},J=function(t,n,e){return t===D&&J(U,n,e),y(t),n=b(n,!0),y(e),r(C,n)?(e.enumerable?(r(t,R)&&t[R][n]&&(t[R][n]=!1),e=x(e,{enumerable:w(0,!1)})):(r(t,R)||P(t,R,w(1,{})),t[R][n]=!0),V(t,n,e)):P(t,n,e)
},K=function(t,n){y(t);for(var r,e=g(n=m(n)),i=0,o=e.length;o>i;)J(t,r=e[i++],n[r]);return t},q=function(t,n){return void 0===n?x(t):K(x(t),n)
},X=function(t){var n=T.call(this,t=b(t,!0));return!(this===D&&r(C,t)&&!r(U,t)||(n||!r(this,t)||!r(C,t)||r(this,R)&&this[R][t])&&!n)
},$=function(t,n){if(t=m(t),n=b(n,!0),t!==D||!r(C,n)||r(U,n)){var e=F(t,n);return!e||!r(C,n)||r(t,R)&&t[R][n]||(e.enumerable=!0),e
}},H=function(t){for(var n,e=M(m(t)),i=[],o=0;e.length>o;)r(C,n=e[o++])||n==R||n==u||i.push(n);return i},Z=function(t){for(var n,e=t===D,i=M(e?U:m(t)),o=[],u=0;i.length>u;)!r(C,n=i[u++])||e&&!r(D,n)||o.push(C[n]);
return o};W||(j=function(){if(this instanceof j)throw TypeError("Symbol is not a constructor!");var t=s(arguments.length>0?arguments[0]:void 0),n=function(e){this===D&&n.call(U,e),r(this,R)&&r(this[R],t)&&(this[R][t]=!1),V(this,t,w(1,e))
};return e&&B&&V(D,t,{configurable:!0,set:n}),z(t)},o(j[N],"toString",function(){return this._k}),S.f=$,E.f=J,t(72).f=_.f=H,t(77).f=X,t(73).f=Z,e&&!t(58)&&o(D,"propertyIsEnumerable",X,!0),h.f=function(t){return z(l(t))
}),i(i.G+i.W+i.F*!W,{Symbol:j});for(var Q="hasInstance,isConcatSpreadable,iterator,match,replace,search,species,split,toPrimitive,toStringTag,unscopables".split(","),tn=0;Q.length>tn;)l(Q[tn++]);
for(var Q=O(l.store),tn=0;Q.length>tn;)v(Q[tn++]);i(i.S+i.F*!W,"Symbol",{"for":function(t){return r(L,t+="")?L[t]:L[t]=j(t)
},keyFor:function(t){if(Y(t))return p(L,t);throw TypeError(t+" is not a symbol!")},useSetter:function(){B=!0},useSimple:function(){B=!1
}}),i(i.S+i.F*!W,"Object",{create:q,defineProperty:J,defineProperties:K,getOwnPropertyDescriptor:$,getOwnPropertyNames:H,getOwnPropertySymbols:Z}),A&&i(i.S+i.F*(!W||c(function(){var t=j();
return"[null]"!=I([t])||"{}"!=I({a:t})||"{}"!=I(Object(t))})),"JSON",{stringify:function(t){if(void 0!==t&&!Y(t)){for(var n,r,e=[t],i=1;arguments.length>i;)e.push(arguments[i++]);
return n=e[1],"function"==typeof n&&(r=n),!r&&d(n)||(n=function(t,n){return r&&(n=r.call(this,t,n)),Y(n)?void 0:n}),e[1]=n,I.apply(A,e)
}}}),j[N][k]||t(40)(j[N],k,j[N].valueOf),a(j,"Symbol"),a(Math,"Math",!0),a(n.JSON,"JSON",!0)},{107:107,110:110,114:114,115:115,116:116,117:117,28:28,31:31,32:32,34:34,38:38,39:39,40:40,47:47,57:57,58:58,62:62,66:66,67:67,7:7,70:70,71:71,72:72,73:73,76:76,77:77,85:85,87:87,92:92,94:94}],244:[function(t){"use strict";
var n=t(32),r=t(113),e=t(112),i=t(7),o=t(105),u=t(108),c=t(49),f=t(38).ArrayBuffer,a=t(95),s=e.ArrayBuffer,l=e.DataView,h=r.ABV&&f.isView,v=s.prototype.slice,p=r.VIEW,g="ArrayBuffer";
n(n.G+n.W+n.F*(f!==s),{ArrayBuffer:s}),n(n.S+n.F*!r.CONSTR,g,{isView:function(t){return h&&h(t)||c(t)&&p in t}}),n(n.P+n.U+n.F*t(34)(function(){return!new s(2).slice(1,void 0).byteLength
}),g,{slice:function(t,n){if(void 0!==v&&void 0===n)return v.call(i(this),t);for(var r=i(this).byteLength,e=o(t,r),c=o(void 0===n?r:n,r),f=new(a(this,s))(u(c-e)),h=new l(this),p=new l(f),g=0;c>e;)p.setUint8(g++,h.getUint8(e++));
return f}}),t(91)(g)},{105:105,108:108,112:112,113:113,32:32,34:34,38:38,49:49,7:7,91:91,95:95}],245:[function(t){var n=t(32);
n(n.G+n.W+n.F*!t(113).ABV,{DataView:t(112).DataView})},{112:112,113:113,32:32}],246:[function(t){t(111)("Float32",4,function(t){return function(n,r,e){return t(this,n,r,e)
}})},{111:111}],247:[function(t){t(111)("Float64",8,function(t){return function(n,r,e){return t(this,n,r,e)}})},{111:111}],248:[function(t){t(111)("Int16",2,function(t){return function(n,r,e){return t(this,n,r,e)
}})},{111:111}],249:[function(t){t(111)("Int32",4,function(t){return function(n,r,e){return t(this,n,r,e)}})},{111:111}],250:[function(t){t(111)("Int8",1,function(t){return function(n,r,e){return t(this,n,r,e)
}})},{111:111}],251:[function(t){t(111)("Uint16",2,function(t){return function(n,r,e){return t(this,n,r,e)}})},{111:111}],252:[function(t){t(111)("Uint32",4,function(t){return function(n,r,e){return t(this,n,r,e)
}})},{111:111}],253:[function(t){t(111)("Uint8",1,function(t){return function(n,r,e){return t(this,n,r,e)}})},{111:111}],254:[function(t){t(111)("Uint8",1,function(t){return function(n,r,e){return t(this,n,r,e)
}},!0)},{111:111}],255:[function(t,n){"use strict";var r,e=t(12)(0),i=t(87),o=t(62),u=t(65),c=t(21),f=t(49),a=o.getWeak,s=Object.isExtensible,l=c.ufstore,h={},v=function(t){return function(){return t(this,arguments.length>0?arguments[0]:void 0)
}},p={get:function(t){if(f(t)){var n=a(t);return n===!0?l(this).get(t):n?n[this._i]:void 0}},set:function(t,n){return c.def(this,t,n)
}},g=n.exports=t(22)("WeakMap",v,p,c,!0,!0);7!=(new g).set((Object.freeze||Object)(h),7).get(h)&&(r=c.getConstructor(v),u(r.prototype,p),o.NEED=!0,e(["delete","has","get","set"],function(t){var n=g.prototype,e=n[t];
i(n,t,function(n,i){if(f(n)&&!s(n)){this._f||(this._f=new r);var o=this._f[t](n,i);return"set"==t?this:o}return e.call(this,n,i)
})}))},{12:12,21:21,22:22,49:49,62:62,65:65,87:87}],256:[function(t){"use strict";var n=t(21);t(22)("WeakSet",function(t){return function(){return t(this,arguments.length>0?arguments[0]:void 0)
}},{add:function(t){return n.def(this,t,!0)}},n,!1,!0)},{21:21,22:22}],257:[function(t){"use strict";var n=t(32),r=t(11)(!0);
n(n.P,"Array",{includes:function(t){return r(this,t,arguments.length>1?arguments[1]:void 0)}}),t(5)("includes")},{11:11,32:32,5:5}],258:[function(t){var n=t(32),r=t(64)(),e=t(38).process,i="process"==t(18)(e);
n(n.G,{asap:function(t){var n=i&&e.domain;r(n?n.bind(t):t)}})},{18:18,32:32,38:38,64:64}],259:[function(t){var n=t(32),r=t(18);
n(n.S,"Error",{isError:function(t){return"Error"===r(t)}})},{18:18,32:32}],260:[function(t){var n=t(32);n(n.P+n.R,"Map",{toJSON:t(20)("Map")})
},{20:20,32:32}],261:[function(t){var n=t(32);n(n.S,"Math",{iaddh:function(t,n,r,e){var i=t>>>0,o=n>>>0,u=r>>>0;return o+(e>>>0)+((i&u|(i|u)&~(i+u>>>0))>>>31)|0
}})},{32:32}],262:[function(t){var n=t(32);n(n.S,"Math",{imulh:function(t,n){var r=65535,e=+t,i=+n,o=e&r,u=i&r,c=e>>16,f=i>>16,a=(c*u>>>0)+(o*u>>>16);
return c*f+(a>>16)+((o*f>>>0)+(a&r)>>16)}})},{32:32}],263:[function(t){var n=t(32);n(n.S,"Math",{isubh:function(t,n,r,e){var i=t>>>0,o=n>>>0,u=r>>>0;
return o-(e>>>0)-((~i&u|~(i^u)&i-u>>>0)>>>31)|0}})},{32:32}],264:[function(t){var n=t(32);n(n.S,"Math",{umulh:function(t,n){var r=65535,e=+t,i=+n,o=e&r,u=i&r,c=e>>>16,f=i>>>16,a=(c*u>>>0)+(o*u>>>16);
return c*f+(a>>>16)+((o*f>>>0)+(a&r)>>>16)}})},{32:32}],265:[function(t){"use strict";var n=t(32),r=t(109),e=t(3),i=t(67);
t(28)&&n(n.P+t(69),"Object",{__defineGetter__:function(t,n){i.f(r(this),t,{get:e(n),enumerable:!0,configurable:!0})}})},{109:109,28:28,3:3,32:32,67:67,69:69}],266:[function(t){"use strict";
var n=t(32),r=t(109),e=t(3),i=t(67);t(28)&&n(n.P+t(69),"Object",{__defineSetter__:function(t,n){i.f(r(this),t,{set:e(n),enumerable:!0,configurable:!0})
}})},{109:109,28:28,3:3,32:32,67:67,69:69}],267:[function(t){var n=t(32),r=t(79)(!0);n(n.S,"Object",{entries:function(t){return r(t)
}})},{32:32,79:79}],268:[function(t){var n=t(32),r=t(80),e=t(107),i=t(70),o=t(24);n(n.S,"Object",{getOwnPropertyDescriptors:function(t){for(var n,u=e(t),c=i.f,f=r(u),a={},s=0;f.length>s;)o(a,n=f[s++],c(u,n));
return a}})},{107:107,24:24,32:32,70:70,80:80}],269:[function(t){"use strict";var n=t(32),r=t(109),e=t(110),i=t(74),o=t(70).f;
t(28)&&n(n.P+t(69),"Object",{__lookupGetter__:function(t){var n,u=r(this),c=e(t,!0);do if(n=o(u,c))return n.get;while(u=i(u))
}})},{109:109,110:110,28:28,32:32,69:69,70:70,74:74}],270:[function(t){"use strict";var n=t(32),r=t(109),e=t(110),i=t(74),o=t(70).f;
t(28)&&n(n.P+t(69),"Object",{__lookupSetter__:function(t){var n,u=r(this),c=e(t,!0);do if(n=o(u,c))return n.set;while(u=i(u))
}})},{109:109,110:110,28:28,32:32,69:69,70:70,74:74}],271:[function(t){var n=t(32),r=t(79)(!1);n(n.S,"Object",{values:function(t){return r(t)
}})},{32:32,79:79}],272:[function(t){"use strict";var n=t(32),r=t(38),e=t(23),i=t(64)(),o=t(117)("observable"),u=t(3),c=t(7),f=t(6),a=t(86),s=t(40),l=t(37),h=l.RETURN,v=function(t){return null==t?void 0:u(t)
},p=function(t){var n=t._c;n&&(t._c=void 0,n())},g=function(t){return void 0===t._o},d=function(t){g(t)||(t._o=void 0,p(t))
},y=function(t,n){c(t),this._c=void 0,this._o=t,t=new m(this);try{var r=n(t),e=r;null!=r&&("function"==typeof r.unsubscribe?r=function(){e.unsubscribe()
}:u(r),this._c=r)}catch(n){return void t.error(n)}g(this)&&p(this)};y.prototype=a({},{unsubscribe:function(){d(this)}});var m=function(t){this._s=t
};m.prototype=a({},{next:function(t){var n=this._s;if(!g(n)){var r=n._o;try{var e=v(r.next);if(e)return e.call(r,t)}catch(t){try{d(n)
}finally{throw t}}}},error:function(t){var n=this._s;if(g(n))throw t;var r=n._o;n._o=void 0;try{var e=v(r.error);if(!e)throw t;
t=e.call(r,t)}catch(t){try{p(n)}finally{throw t}}return p(n),t},complete:function(t){var n=this._s;if(!g(n)){var r=n._o;n._o=void 0;
try{var e=v(r.complete);t=e?e.call(r,t):void 0}catch(t){try{p(n)}finally{throw t}}return p(n),t}}});var b=function(t){f(this,b,"Observable","_f")._f=u(t)
};a(b.prototype,{subscribe:function(t){return new y(t,this._f)},forEach:function(t){var n=this;return new(e.Promise||r.Promise)(function(r,e){u(t);
var i=n.subscribe({next:function(t){try{return n(t)}catch(n){e(n),i.unsubscribe()}},error:e,complete:r})})}}),a(b,{from:function(t){var n="function"==typeof this?this:b,r=v(c(t)[o]);
if(r){var e=c(r.call(t));return e.constructor===n?e:new n(function(t){return e.subscribe(t)})}return new n(function(t){var n=!1;
return i(function(){if(!n){try{if(l(r,!1,function(r){return t.next(r),n?h:void 0})===h)return}catch(r){if(n)throw r;return void t.error(r)
}t.complete()}}),function(){n=!0}})},of:function(){for(var t=0,n=arguments.length,r=Array(n);n>t;)r[t]=arguments[t++];return new("function"==typeof this?this:b)(function(t){var n=!1;
return i(function(){if(!n){for(var e=0;e<r.length;++e)if(t.next(r[e]),n)return;t.complete()}}),function(){n=!0}})}}),s(b.prototype,o,function(){return this
}),n(n.G,{Observable:b}),t(91)("Observable")},{117:117,23:23,3:3,32:32,37:37,38:38,40:40,6:6,64:64,7:7,86:86,91:91}],273:[function(t){var n=t(63),r=t(7),e=n.key,i=n.set;
n.exp({defineMetadata:function(t,n,o,u){i(t,n,r(o),e(u))}})},{63:63,7:7}],274:[function(t){var n=t(63),r=t(7),e=n.key,i=n.map,o=n.store;
n.exp({deleteMetadata:function(t,n){var u=arguments.length<3?void 0:e(arguments[2]),c=i(r(n),u,!1);if(void 0===c||!c.delete(t))return!1;
if(c.size)return!0;var f=o.get(n);return f.delete(u),!!f.size||o.delete(n)}})},{63:63,7:7}],275:[function(t){var n=t(220),r=t(10),e=t(63),i=t(7),o=t(74),u=e.keys,c=e.key,f=function(t,e){var i=u(t,e),c=o(t);
if(null===c)return i;var a=f(c,e);return a.length?i.length?r(new n(i.concat(a))):a:i};e.exp({getMetadataKeys:function(t){return f(i(t),arguments.length<2?void 0:c(arguments[1]))
}})},{10:10,220:220,63:63,7:7,74:74}],276:[function(t){var n=t(63),r=t(7),e=t(74),i=n.has,o=n.get,u=n.key,c=function(t,n,r){var u=i(t,n,r);
if(u)return o(t,n,r);var f=e(n);return null!==f?c(t,f,r):void 0};n.exp({getMetadata:function(t,n){return c(t,r(n),arguments.length<3?void 0:u(arguments[2]))
}})},{63:63,7:7,74:74}],277:[function(t){var n=t(63),r=t(7),e=n.keys,i=n.key;n.exp({getOwnMetadataKeys:function(t){return e(r(t),arguments.length<2?void 0:i(arguments[1]))
}})},{63:63,7:7}],278:[function(t){var n=t(63),r=t(7),e=n.get,i=n.key;n.exp({getOwnMetadata:function(t,n){return e(t,r(n),arguments.length<3?void 0:i(arguments[2]))
}})},{63:63,7:7}],279:[function(t){var n=t(63),r=t(7),e=t(74),i=n.has,o=n.key,u=function(t,n,r){var o=i(t,n,r);if(o)return!0;
var c=e(n);return null!==c&&u(t,c,r)};n.exp({hasMetadata:function(t,n){return u(t,r(n),arguments.length<3?void 0:o(arguments[2]))
}})},{63:63,7:7,74:74}],280:[function(t){var n=t(63),r=t(7),e=n.has,i=n.key;n.exp({hasOwnMetadata:function(t,n){return e(t,r(n),arguments.length<3?void 0:i(arguments[2]))
}})},{63:63,7:7}],281:[function(t){var n=t(63),r=t(7),e=t(3),i=n.key,o=n.set;n.exp({metadata:function(t,n){return function(u,c){o(t,n,(void 0!==c?r:e)(u),i(c))
}}})},{3:3,63:63,7:7}],282:[function(t){var n=t(32);n(n.P+n.R,"Set",{toJSON:t(20)("Set")})},{20:20,32:32}],283:[function(t){"use strict";
var n=t(32),r=t(97)(!0);n(n.P,"String",{at:function(t){return r(this,t)}})},{32:32,97:97}],284:[function(t){"use strict";
var n=t(32),r=t(27),e=t(108),i=t(50),o=t(36),u=RegExp.prototype,c=function(t,n){this._r=t,this._s=n};t(52)(c,"RegExp String",function(){var t=this._r.exec(this._s);
return{value:t,done:null===t}}),n(n.P,"String",{matchAll:function(t){if(r(this),!i(t))throw TypeError(t+" is not a regexp!");
var n=String(this),f="flags"in u?String(t.flags):o.call(t),a=new RegExp(t.source,~f.indexOf("g")?f:"g"+f);return a.lastIndex=e(t.lastIndex),new c(a,n)
}})},{108:108,27:27,32:32,36:36,50:50,52:52}],285:[function(t){"use strict";var n=t(32),r=t(100);n(n.P,"String",{padEnd:function(t){return r(this,t,arguments.length>1?arguments[1]:void 0,!1)
}})},{100:100,32:32}],286:[function(t){"use strict";var n=t(32),r=t(100);n(n.P,"String",{padStart:function(t){return r(this,t,arguments.length>1?arguments[1]:void 0,!0)
}})},{100:100,32:32}],287:[function(t){"use strict";t(102)("trimLeft",function(t){return function(){return t(this,1)}},"trimStart")
},{102:102}],288:[function(t){"use strict";t(102)("trimRight",function(t){return function(){return t(this,2)}},"trimEnd")
},{102:102}],289:[function(t){t(115)("asyncIterator")},{115:115}],290:[function(t){t(115)("observable")},{115:115}],291:[function(t){var n=t(32);
n(n.S,"System",{global:t(38)})},{32:32,38:38}],292:[function(t){for(var n=t(130),r=t(87),e=t(38),i=t(40),o=t(56),u=t(117),c=u("iterator"),f=u("toStringTag"),a=o.Array,s=["NodeList","DOMTokenList","MediaList","StyleSheetList","CSSRuleList"],l=0;5>l;l++){var h,v=s[l],p=e[v],g=p&&p.prototype;
if(g){g[c]||i(g,c,a),g[f]||i(g,f,v),o[v]=a;for(h in n)g[h]||r(g,h,n[h],!0)}}},{117:117,130:130,38:38,40:40,56:56,87:87}],293:[function(t){var n=t(32),r=t(104);
n(n.G+n.B,{setImmediate:r.set,clearImmediate:r.clear})},{104:104,32:32}],294:[function(t){var n=t(38),r=t(32),e=t(44),i=t(83),o=n.navigator,u=!!o&&/MSIE .\./.test(o.userAgent),c=function(t){return u?function(n,r){return t(e(i,[].slice.call(arguments,2),"function"==typeof n?n:Function(n)),r)
}:t};r(r.G+r.B+r.F*u,{setTimeout:c(n.setTimeout),setInterval:c(n.setInterval)})},{32:32,38:38,44:44,83:83}],295:[function(t,n){t(243),t(180),t(182),t(181),t(184),t(186),t(191),t(185),t(183),t(193),t(192),t(188),t(189),t(187),t(179),t(190),t(194),t(195),t(146),t(148),t(147),t(197),t(196),t(167),t(177),t(178),t(168),t(169),t(170),t(171),t(172),t(173),t(174),t(175),t(176),t(150),t(151),t(152),t(153),t(154),t(155),t(156),t(157),t(158),t(159),t(160),t(161),t(162),t(163),t(164),t(165),t(166),t(230),t(235),t(242),t(233),t(225),t(226),t(231),t(236),t(238),t(221),t(222),t(223),t(224),t(227),t(228),t(229),t(232),t(234),t(237),t(239),t(240),t(241),t(141),t(143),t(142),t(145),t(144),t(129),t(127),t(134),t(131),t(137),t(139),t(126),t(133),t(123),t(138),t(121),t(136),t(135),t(128),t(132),t(120),t(122),t(125),t(124),t(140),t(130),t(213),t(219),t(214),t(215),t(216),t(217),t(218),t(198),t(149),t(220),t(255),t(256),t(244),t(245),t(250),t(253),t(254),t(248),t(251),t(249),t(252),t(246),t(247),t(199),t(200),t(201),t(202),t(203),t(206),t(204),t(205),t(207),t(208),t(209),t(210),t(212),t(211),t(257),t(283),t(286),t(285),t(287),t(288),t(284),t(289),t(290),t(268),t(271),t(267),t(265),t(266),t(269),t(270),t(260),t(282),t(291),t(259),t(261),t(263),t(262),t(264),t(273),t(274),t(276),t(275),t(278),t(277),t(279),t(280),t(281),t(258),t(272),t(294),t(293),t(292),n.exports=t(23)
},{120:120,121:121,122:122,123:123,124:124,125:125,126:126,127:127,128:128,129:129,130:130,131:131,132:132,133:133,134:134,135:135,136:136,137:137,138:138,139:139,140:140,141:141,142:142,143:143,144:144,145:145,146:146,147:147,148:148,149:149,150:150,151:151,152:152,153:153,154:154,155:155,156:156,157:157,158:158,159:159,160:160,161:161,162:162,163:163,164:164,165:165,166:166,167:167,168:168,169:169,170:170,171:171,172:172,173:173,174:174,175:175,176:176,177:177,178:178,179:179,180:180,181:181,182:182,183:183,184:184,185:185,186:186,187:187,188:188,189:189,190:190,191:191,192:192,193:193,194:194,195:195,196:196,197:197,198:198,199:199,200:200,201:201,202:202,203:203,204:204,205:205,206:206,207:207,208:208,209:209,210:210,211:211,212:212,213:213,214:214,215:215,216:216,217:217,218:218,219:219,220:220,221:221,222:222,223:223,224:224,225:225,226:226,227:227,228:228,229:229,23:23,230:230,231:231,232:232,233:233,234:234,235:235,236:236,237:237,238:238,239:239,240:240,241:241,242:242,243:243,244:244,245:245,246:246,247:247,248:248,249:249,250:250,251:251,252:252,253:253,254:254,255:255,256:256,257:257,258:258,259:259,260:260,261:261,262:262,263:263,264:264,265:265,266:266,267:267,268:268,269:269,270:270,271:271,272:272,273:273,274:274,275:275,276:276,277:277,278:278,279:279,280:280,281:281,282:282,283:283,284:284,285:285,286:286,287:287,288:288,289:289,290:290,291:291,292:292,293:293,294:294}],296:[function(t,n){(function(t){!function(t){"use strict";
function r(t,n,r,e){var o=n&&n.prototype instanceof i?n:i,u=Object.create(o.prototype),c=new h(e||[]);return u._invoke=a(t,r,c),u
}function e(t,n,r){try{return{type:"normal",arg:t.call(n,r)}}catch(t){return{type:"throw",arg:t}}}function i(){}function o(){}function u(){}function c(t){["next","throw","return"].forEach(function(n){t[n]=function(t){return this._invoke(n,t)
}})}function f(t){function n(r,i,o,u){var c=e(t[r],t,i);if("throw"!==c.type){var f=c.arg,a=f.value;return a&&"object"==typeof a&&y.call(a,"__await")?Promise.resolve(a.__await).then(function(t){n("next",t,o,u)
},function(t){n("throw",t,o,u)}):Promise.resolve(a).then(function(t){f.value=t,o(f)},u)}u(c.arg)}function r(t,r){function e(){return new Promise(function(e,i){n(t,r,e,i)
})}return i=i?i.then(e,e):e()}"object"==typeof process&&process.domain&&(n=process.domain.bind(n));var i;this._invoke=r}function a(t,n,r){var i=S;
return function(o,u){if(i===O)throw new Error("Generator is already running");if(i===F){if("throw"===o)throw u;return p()
}for(;;){var c=r.delegate;if(c){if("return"===o||"throw"===o&&c.iterator[o]===g){r.delegate=null;var f=c.iterator.return;
if(f){var a=e(f,c.iterator,u);if("throw"===a.type){o="throw",u=a.arg;continue}}if("return"===o)continue}var a=e(c.iterator[o],c.iterator,u);
if("throw"===a.type){r.delegate=null,o="throw",u=a.arg;continue}o="next",u=g;var s=a.arg;if(!s.done)return i=E,s;r[c.resultName]=s.value,r.next=c.nextLoc,r.delegate=null
}if("next"===o)r.sent=r._sent=u;else if("throw"===o){if(i===S)throw i=F,u;r.dispatchException(u)&&(o="next",u=g)}else"return"===o&&r.abrupt("return",u);
i=O;var a=e(t,n,r);if("normal"===a.type){i=r.done?F:E;var s={value:a.arg,done:r.done};if(a.arg!==P)return s;r.delegate&&"next"===o&&(u=g)
}else"throw"===a.type&&(i=F,o="throw",u=a.arg)}}}function s(t){var n={tryLoc:t[0]};1 in t&&(n.catchLoc=t[1]),2 in t&&(n.finallyLoc=t[2],n.afterLoc=t[3]),this.tryEntries.push(n)
}function l(t){var n=t.completion||{};n.type="normal",delete n.arg,t.completion=n}function h(t){this.tryEntries=[{tryLoc:"root"}],t.forEach(s,this),this.reset(!0)
}function v(t){if(t){var n=t[b];if(n)return n.call(t);if("function"==typeof t.next)return t;if(!isNaN(t.length)){var r=-1,e=function i(){for(;++r<t.length;)if(y.call(t,r))return i.value=t[r],i.done=!1,i;
return i.value=g,i.done=!0,i};return e.next=e}}return{next:p}}function p(){return{value:g,done:!0}}var g,d=Object.prototype,y=d.hasOwnProperty,m="function"==typeof Symbol?Symbol:{},b=m.iterator||"@@iterator",w=m.toStringTag||"@@toStringTag",x="object"==typeof n,_=t.regeneratorRuntime;
if(_)return void(x&&(n.exports=_));_=t.regeneratorRuntime=x?n.exports:{},_.wrap=r;var S="suspendedStart",E="suspendedYield",O="executing",F="completed",P={},M={};
M[b]=function(){return this};var j=Object.getPrototypeOf,A=j&&j(j(v([])));A&&A!==d&&y.call(A,b)&&(M=A);var I=u.prototype=i.prototype=Object.create(M);
o.prototype=I.constructor=u,u.constructor=o,u[w]=o.displayName="GeneratorFunction",_.isGeneratorFunction=function(t){var n="function"==typeof t&&t.constructor;
return!!n&&(n===o||"GeneratorFunction"===(n.displayName||n.name))},_.mark=function(t){return Object.setPrototypeOf?Object.setPrototypeOf(t,u):(t.__proto__=u,w in t||(t[w]="GeneratorFunction")),t.prototype=Object.create(I),t
},_.awrap=function(t){return{__await:t}},c(f.prototype),_.AsyncIterator=f,_.async=function(t,n,e,i){var o=new f(r(t,n,e,i));
return _.isGeneratorFunction(n)?o:o.next().then(function(t){return t.done?t.value:o.next()})},c(I),I[w]="Generator",I.toString=function(){return"[object Generator]"
},_.keys=function(t){var n=[];for(var r in t)n.push(r);return n.reverse(),function e(){for(;n.length;){var r=n.pop();if(r in t)return e.value=r,e.done=!1,e
}return e.done=!0,e}},_.values=v,h.prototype={constructor:h,reset:function(t){if(this.prev=0,this.next=0,this.sent=this._sent=g,this.done=!1,this.delegate=null,this.tryEntries.forEach(l),!t)for(var n in this)"t"===n.charAt(0)&&y.call(this,n)&&!isNaN(+n.slice(1))&&(this[n]=g)
},stop:function(){this.done=!0;var t=this.tryEntries[0],n=t.completion;if("throw"===n.type)throw n.arg;return this.rval},dispatchException:function(t){function n(n,e){return o.type="throw",o.arg=t,r.next=n,!!e
}if(this.done)throw t;for(var r=this,e=this.tryEntries.length-1;e>=0;--e){var i=this.tryEntries[e],o=i.completion;if("root"===i.tryLoc)return n("end");
if(i.tryLoc<=this.prev){var u=y.call(i,"catchLoc"),c=y.call(i,"finallyLoc");if(u&&c){if(this.prev<i.catchLoc)return n(i.catchLoc,!0);
if(this.prev<i.finallyLoc)return n(i.finallyLoc)}else if(u){if(this.prev<i.catchLoc)return n(i.catchLoc,!0)}else{if(!c)throw new Error("try statement without catch or finally");
if(this.prev<i.finallyLoc)return n(i.finallyLoc)}}}},abrupt:function(t,n){for(var r=this.tryEntries.length-1;r>=0;--r){var e=this.tryEntries[r];
if(e.tryLoc<=this.prev&&y.call(e,"finallyLoc")&&this.prev<e.finallyLoc){var i=e;break}}i&&("break"===t||"continue"===t)&&i.tryLoc<=n&&n<=i.finallyLoc&&(i=null);
var o=i?i.completion:{};return o.type=t,o.arg=n,i?this.next=i.finallyLoc:this.complete(o),P},complete:function(t,n){if("throw"===t.type)throw t.arg;
"break"===t.type||"continue"===t.type?this.next=t.arg:"return"===t.type?(this.rval=t.arg,this.next="end"):"normal"===t.type&&n&&(this.next=n)
},finish:function(t){for(var n=this.tryEntries.length-1;n>=0;--n){var r=this.tryEntries[n];if(r.finallyLoc===t)return this.complete(r.completion,r.afterLoc),l(r),P
}},"catch":function(t){for(var n=this.tryEntries.length-1;n>=0;--n){var r=this.tryEntries[n];if(r.tryLoc===t){var e=r.completion;
if("throw"===e.type){var i=e.arg;l(r)}return i}}throw new Error("illegal catch attempt")},delegateYield:function(t,n,r){return this.delegate={iterator:v(t),resultName:n,nextLoc:r},P
}}}("object"==typeof t?t:"object"==typeof window?window:"object"==typeof self?self:this)}).call(this,"undefined"!=typeof global?global:"undefined"!=typeof self?self:"undefined"!=typeof window?window:{})
},{}]},{},[1]);
;define("translation:widget/common/environment",function(n,t){"use strict";var e={};t.set=function(n,t){e[n]=t},t.get=function(n){return e[n]
},t.remove=function(n){delete e[n]},t.has=function(n){return e.hasOwnProperty(n)}});
;define("translation:widget/common/collGroupUtil",function(n,r,e){"use strict";function t(){return new Promise(function(n,r){$.ajax({url:"/myconf",type:"GET",dataType:"json",cache:!1,success:function(r){r.defaultOn=Boolean(r.defaultOn),r.defaultGid=parseInt(r.defaultGid,10);
var e=-1;r.defaultOn&&(e=r.defaultGid),n(e)},error:function(){r(),alert("接口请求失败")}})})}function o(){return new Promise(function(n,r){$.ajax({url:"/pccollgroup?req=list",type:"GET",dataType:"json",cache:!1,success:function(e){0===e.errno?(e.data.forEach(function(n){n.id=parseInt(n.id,10)
}),n(e.data)):(r(),alert("接口返回错误，错误码 "+e.errno))},error:function(){r(),alert("接口请求失败")}})})}function a(n,r){return new Promise(function(e,t){n=n.trim();
var o=s(n);o.isValid?$.ajax({url:"/pccollgroup?req=add",type:"POST",dataType:"json",data:{name:n,isDefault:r?1:0,bdstoken:window.bdstoken},success:function(n){0===n.errno?e(parseInt(n.data.gid,10)):t("接口返回错误，错误码 "+n.errno)
},error:function(){t("接口请求失败")}}):t(o.errMsg)})}function u(n,r,e){return new Promise(function(t,o){n=parseInt(n,10),r=r.trim();
var a=s(r,!0,n);a.isValid?$.ajax({url:"/pccollgroup?req=edit",type:"POST",dataType:"json",data:{gid:n,name:r,isDefault:e?1:0,bdstoken:window.bdstoken},success:function(n){0===n.errno?t(parseInt(n.data.gid,10)):o("接口返回错误，错误码 "+n.errno)
},error:function(){o("接口请求失败")}}):o(a.errMsg)})}function s(n){var r=arguments.length>1&&void 0!==arguments[1]?arguments[1]:!1,e=arguments[2],t={isValid:!1,errMsg:""},o=!1,a=[];
f.has("groupInfo")?a=f.get("groupInfo").groupMap.values():f.has("collInfo")&&(a=f.get("collInfo").groupList);var u=!0,s=!1,i=void 0;
try{for(var c,l=a[Symbol.iterator]();!(u=(c=l.next()).done);u=!0){var d=c.value,p=d.name,g=d.id;if(n===p){if(r&&e===g)break;
o=!0;break}}}catch(y){s=!0,i=y}finally{try{!u&&l.return&&l.return()}finally{if(s)throw i}}return o?(t.errMsg="该分组已存在！",t):0===n.length?(t.errMsg="组名不能为空！",t):n.length>50?(t.errMsg="名称超过50个字符。",t):(t.isValid=!0,t)
}function i(n){return new Promise(function(r,e){n=parseInt(n,10),$.ajax({url:"/pccollgroup?req=del",type:"POST",dataType:"json",data:{gid:n,bdstoken:window.bdstoken},success:function(n){0===n.errno?r():(e(),alert("接口错误，错误码"+n.errno))
},error:function(){e(),alert("接口请求失败")}})})}function c(n){return new Promise(function(r,e){n=parseInt(n,10);var t={bdstoken:window.bdstoken};
-1===n?t.defaultOn=0:(t.defaultOn=1,t.defaultGid=n),$.ajax({url:"/pccollgroup?req=defaultset",type:"POST",dataType:"json",data:t,success:function(n){0===n.errno?r():(e(),alert("接口错误，错误码"+n.errno))
},error:function(){e(),alert("接口请求失败")}})})}function l(n,r){return new Promise(function(e,t){n=n.map(function(n){return parseInt(n,10)
}),r=parseInt(r,10),$.ajax({url:"/pccollgroup?req=movequery",type:"POST",dataType:"json",data:{id:n,gid:r,bdstoken:window.bdstoken},success:function(n){0===n.errno?e():(t(),alert("接口错误，错误码"+n.errno))
},error:function(){t(),alert("接口请求失败")}})})}function d(n){return new Promise(function(r,e){var t={};n=parseInt(n,10),-1!==n&&(t.gid=n),$.ajax({url:"/pccollgroup?req=getcnt",type:"GET",dataType:"json",cache:!1,data:t,success:function(n){0===n.errno?r(parseInt(n.data.cnt,10)):(e(),alert("接口错误，错误码"+n.errno))
},error:function(){e(),alert("接口请求失败")}})})}var f=n("translation:widget/common/environment");e.exports={getDefaultGroupAsync:t,getCollGroupListAsync:o,newCollGroupAsync:a,editCollGroupAsync:u,deleteCollGroupAsync:i,setDefaultGroupAsync:c,moveToGroupAsync:l,getGroupCountAsync:d}
});
;define("translation:widget/common/config/global",function(n){"use strict";var t=n("translation:widget/common/environment"),o={init:function(){t.set("queryInCheck",!1),t.set("doubleLangChecked",!1),t.set("fromLangIsAuto",!0),t.set("langList",window.common.langList),t.set("remote",window.common.remote),t.set("langMap",window.common.langMap),t.set("account",window.common.account),t.set("rtl",window.common.rtl),t.set("maxCollectionCount",2e3),t.set("isProcessOcrResult",!1),t.set("shouldClearPromptOnInput",!1),t.set("ocrLangList",["zh","de","en","spa","it","fra","pt","ru","jp","kor"])
}};o.init()});
;define("translation:widget/common/config/sound",function(t,e){"use strict";function n(){return/msie/i.test(navigator.userAgent)===!0?!0:!1
}e.getMaxSoundLength=function(t){if(t){var e={l:{ie:670,oth:1e3},m:{ie:180,oth:200},s:{ie:100,oth:120},x:{ie:50,oth:60}},r={en:e.l,zh:e.l,yue:e.l,kor:e.l,jp:e.l,ara:e.x,th:e.m,pt:e.x,spa:e.s,fra:e.s,ru:e.m,de:e.x},i=n()?r[t]&&r[t].ie?r[t].ie:670:r[t]&&r[t].oth?r[t].oth:1200;
return i}var i=n()?670:1200;return i},e.isLangSound=function(t){var e=["en","zh","yue","ara","kor","jp","th","pt","spa","fra","ru","de"];
return $.inArray(t,e)>=0?!0:!1}});
;define("translation:widget/common/config/trans",function(n,_){"use strict";_.MAX_QUERY_COUNT=5e3,_.MAX_URL_COUNT=2e3,_.MAX_COLLECTION_COUNT=2e3,_.MAX_SHARE_COUNT=120
});
;define("translation:widget/common/cookie",function(e,n){"use strict";function t(e){return new RegExp('^[^\\x00-\\x20\\x7f\\(\\)<>@,;:\\\\\\"\\[\\]\\?=\\{\\}\\/\\u0080-\\uffff]+$').test(e)
}n.setCookie=function(e,n,i){if(t(e)){i=i||{};var o=i.expires;"number"==typeof i.expires&&(o=new Date,o.setTime(o.getTime()+i.expires)),document.cookie=e+"="+n+(i.path?"; path="+i.path:"")+(o?"; expires="+o.toGMTString():"")+(i.domain?"; model="+i.domain:"")+(i.secure?"; secure":"")
}},n.getCookie=function(e){if(t(e)){var n=new RegExp("(^| )"+e+"=([^;]*)(;|$)"),i=n.exec(document.cookie);if(i)return i[2]||null
}return null}});
;define("translation:widget/common/flash",function(){"use strict";var a=$("#btpm-swf-src").attr("src"),e=/msie/i.test(navigator.userAgent)===!0,t={},r=navigator.userAgent.toLowerCase(),n=void 0;
(n=r.match(/rv:([\d.]+)\) like gecko/))?t.ie=parseInt(n[1],10):(n=r.match(/msie ([\d.]+)/))?t.ie=parseInt(n[1],10):0;var i={isIE:['<object id="bt-flash-object" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" width="200" height="200" align="middle">','<param name="movie" value="'+a+'" />','<param name="allowScriptAccess" value="always" />','<param name="allowNetworking" value="all" />','<param name="flashVars" value="_instanceName=BTPM" />','<param name="wmode" value="window" />','<param name="scale" value="noscale" />','<param name="salign" value="lt" />',"</object>"].join(""),notIE:['<embed id="bt-flash-embed" src="'+a+'"','width="0" height="0" allowScriptAccess="always"','allowNetworking="all" flashVars="_instanceName=BTPM"','quality="high" type="application/x-shockwave-flash"','pluginspage="http://www.adobe.com/go/getflashplayer"></embed>'].join("")}[e?"isIE":"notIE"];
e=e&&t.ie&&t.ie<9,e?($(".bt-flash").html(i),window.BTPM=$("#bt-flash-object")[0]):($("body").append($('<audio id="dictVoice" style="display: none"></audio>')),window.BTPM=$("#dictVoice")[0]),BTPM.stage=function(){return e?"IE":"noIE"
}(),BTPM.setUrl=function(a){if(!e)return BTPM.src=a,BTPM.load(a),void BTPM.play();try{BTPM._setVar&&BTPM._setVar("url",a)
}catch(t){}return BTPM},BTPM.getUrl=function(){try{if(BTPM._getVar)return BTPM._getVar("url")}catch(a){}return BTPM},BTPM.doPause=function(){try{e&&BTPM._pause&&BTPM._pause(),!e&&BTPM.pause&&BTPM.pause()
}catch(a){}return BTPM},BTPM.getCurrentPosition=function(){return BTPM._getVar?BTPM._getVar("currentPosition")/1e3:BTPM},BTPM.getDuration=function(){return BTPM._getVar?BTPM._getVar("duration")/1e3:BTPM
},BTPM.getDownloadProgress=function(){return BTPM._getVar?100*BTPM._getVar("downloadProgress"):BTPM}});
;define("translation:widget/common/log",function(n,t,r){"use strict";var o=function(){function n(n){var t="_rpLog-"+(new Date).getTime(),r=new Image;
window[t]=r,r.onload=r.onerror=function(){window[t]=null},r.src=n,r=null}function t(n,t){var r,o=n||{};for(r in t)t.hasOwnProperty(r)&&(o[r]=t[r]);
return o}function r(r){for(var o,e=r.logUrl,a=[],u={},i=1,l=arguments.length;l>i;i++)"[object Object]"===Object.prototype.toString.call(arguments[i])&&(u=t(u,arguments[i]));
for(var o in r)"logUrl"!==o&&a.push(o+"="+r[o]);for(var o in u)a.push(o+"="+u[o]);n(e+a.join("&")),"[object Function]"===Object.prototype.toString.call(arguments[arguments.length-1])&&arguments[arguments.length-1].call()
}return{send:r}}();r.exports=o});
;define("translation:widget/common/sendLog",function(n,e){"use strict";var t=n("translation:widget/common/log"),o={logUrl:"https://click.fanyi.baidu.com?",src:1,locate:"zh"};
e.sendIndexClickLog=function(n){t.send(o,n,{type:2,page:1})},e.sendIndexDisplayLog=function(n){t.send(o,n,{type:1,page:1})
},e.sendIndexFeatureLog=function(n){t.send(o,n,{type:3,page:1})},e.sendNewTranspageDisplayLog=function(n){t.send(o,n,{type:1,page:2})
}});
;define("translation:widget/common/soundURIGenerator",function(n,e){"use strict";var t=n("translation:widget/common/environment"),a={generateURI:function(n){n.lan=n.lan||"en";
var e=3,a=t.get("soundSpdMode");return e="normal"===a?3:"slow"===a?1:5,"zh"===n.lan?e+=2:"en"===n.lan?n.lan=t.get("soundPreferMode"):/&lock/.test(n.lan)&&(n.lan=n.lan.split("&")[0]),"/gettts?lan="+n.lan+"&text="+n.text+"&spd="+e+"&source=web"
}};e.generateURI=function(n){return a.generateURI(n)}});
;define("translation:widget/common/stat",function(){"use strict";function t(){$(document).on("click","[data-stat-id]",function(t){var a=t.currentTarget;
if(!a.hasAttribute("data-stat-is-using-uni-stat")||"true"!==a.getAttribute("data-stat-is-using-uni-stat")){var i={"/collection":"收藏","/":"首页","/index":"首页"},e={1:"导出点击",2:"导出-excel点击",3:"导出-txt点击",4:"查找按钮点击",5:"发起查找（回车）",6:"筛选，区分筛选项和选中的值",7:"二次翻译点击",8:"编辑译文点击",9:"编辑确认点击",10:"批量管理点击",11:"批量管理-全部选择点击",12:"批量管理-单条删除点击",13:"批量管理-删除点击",14:"发音按钮点击",15:"首次进入收藏夹页面，【无结果】的展现",18:"输入框收藏分组点击",21:"划词翻译框收藏分组点击",22:"设置按钮点击",25:"导出按钮点击",27:"导出弹窗按分组导出点击",28:"创建新收藏分组点击",29:"点击左侧栏分组列表中分组",31:"编辑功能点击",32:"删除功能点击",33:"快速切换分组点击",37:"点击目录项",38:"报错icon点击量",39:"报错-质量不佳点击量",40:"报错-用词不当点击量",41:"报错-语法有误点击量",42:"报错-其他点击量",43:"报错-提交按钮点击量",44:"unbox中跳转的点击量",45:"entry中跳转的点击量",50:"翻译家推荐-图片点击次数",60:"复制icon点击量",62:"排序icon点击量",63:"排序浮层恢复默认点击次数",69:"词典结果二次查词点击",70:"英英释义二次查词点击",71:"同义词辨析二次查词点击",72:"百度wifi翻译机入口点击",75:"词语用例_词语搭配二次查词点击量",76:"词语用例_短语动词二次查词点击量",77:"同反义词二次查词点击量",79:"词语用例_跳转点击量",88:"百度视频翻译入口点击"},n=a.getAttribute("data-stat-id"),s=i[document.location.pathname];
void 0===s&&(s=document.location.pathname);var u=n+"_";a.hasAttribute("data-stat-is-prefix-page-name")&&"false"===a.getAttribute("data-stat-is-prefix-page-name")||(u=u+s+"页面_"),u+=e[n],a.hasAttribute("data-stat-add")&&(u=u+"_"+a.getAttribute("data-stat-add")),_hmt.push(["_trackEvent",s,u])
}})}$(function(){t()})});
;define("translation:widget/common/string",function(t,r){"use strict";function u(t){return/^[\u0391-\uFFE5]+$/.test(t)}function e(t){var r=/^[\u0600-\u06ff]+$/.test(t)||/^[\ufb50\ufdff]+$/.test(t)||/^[\ufe70-\ufefc]+$/.test(t)||/^[\u0400-\u052F]+$/.test(t);
return r}function n(t,r){var u,e,n,f=0;if(r=r?r.toLowerCase():"","utf-16"===r||"utf16"===r)for(e=0,n=t.length;n>e;e++)u=t.charCodeAt(e),f+=65535>=u?2:4;
else for(e=0,n=t.length;n>e;e++)u=t.charCodeAt(e),f+=127>=u?1:2047>=u?2:65535>=u?3:4;return f}r.getByte=function(t){return n(t)
},r.cutByByteOld=function(t,r,n){for(var f=0,s="",o=0,i=t.length;i>o;o++){var c=0;if(c=e(t.substr(o,1))?2:u(t.substr(o,1))?3:1,f+=c,f>n)return s;
f>r&&(s+=t.substr(o,1))}return s},r.cutByByte=function(t,r,u){for(var e=0,f="",s=0,o=t.length;o>s;s++){var i=n(t.substr(s,1));
if(e+=i,e>u)return f;e>r&&(f+=t.substr(s,1))}return f}});
;define("translation:widget/common/third_party/bowser",function(e,i,s){function r(e){function i(i){var s=e.match(i);return s&&s.length>1&&s[1]||""
}function s(i){var s=e.match(i);return s&&s.length>1&&s[2]||""}function r(e){switch(e){case"NT":return"NT";case"XP":return"XP";
case"NT 5.0":return"2000";case"NT 5.1":return"XP";case"NT 5.2":return"2003";case"NT 6.0":return"Vista";case"NT 6.1":return"7";
case"NT 6.2":return"8";case"NT 6.3":return"8.1";case"NT 10.0":return"10";default:return void 0}}var o,n=i(/(ipod|iphone|ipad)/i).toLowerCase(),t=/like android/i.test(e),a=!t&&/android/i.test(e),d=/nexus\s*[0-6]\s*/i.test(e),v=!d&&/nexus\s*[0-9]+/i.test(e),p=/CrOS/.test(e),l=/silk/i.test(e),c=/sailfish/i.test(e),u=/tizen/i.test(e),f=/(web|hpw)os/i.test(e),h=/windows phone/i.test(e),w=(/SamsungBrowser/i.test(e),!h&&/windows/i.test(e)),b=!n&&!l&&/macintosh/i.test(e),g=!a&&!c&&!u&&!f&&/linux/i.test(e),k=i(/edge\/(\d+(\.\d+)?)/i),x=i(/version\/(\d+(\.\d+)?)/i),y=/tablet/i.test(e)&&!/tablet pc/i.test(e),S=!y&&/[^-]mobi/i.test(e),B=/xbox/i.test(e);
/opera/i.test(e)?o={name:"Opera",opera:m,version:x||i(/(?:opera|opr|opios)[\s\/](\d+(\.\d+)?)/i)}:/opr\/|opios/i.test(e)?o={name:"Opera",opera:m,version:i(/(?:opr|opios)[\s\/](\d+(\.\d+)?)/i)||x}:/SamsungBrowser/i.test(e)?o={name:"Samsung Internet for Android",samsungBrowser:m,version:x||i(/(?:SamsungBrowser)[\s\/](\d+(\.\d+)?)/i)}:/coast/i.test(e)?o={name:"Opera Coast",coast:m,version:x||i(/(?:coast)[\s\/](\d+(\.\d+)?)/i)}:/yabrowser/i.test(e)?o={name:"Yandex Browser",yandexbrowser:m,version:x||i(/(?:yabrowser)[\s\/](\d+(\.\d+)?)/i)}:/ucbrowser/i.test(e)?o={name:"UC Browser",ucbrowser:m,version:i(/(?:ucbrowser)[\s\/](\d+(?:\.\d+)+)/i)}:/mxios/i.test(e)?o={name:"Maxthon",maxthon:m,version:i(/(?:mxios)[\s\/](\d+(?:\.\d+)+)/i)}:/epiphany/i.test(e)?o={name:"Epiphany",epiphany:m,version:i(/(?:epiphany)[\s\/](\d+(?:\.\d+)+)/i)}:/puffin/i.test(e)?o={name:"Puffin",puffin:m,version:i(/(?:puffin)[\s\/](\d+(?:\.\d+)?)/i)}:/sleipnir/i.test(e)?o={name:"Sleipnir",sleipnir:m,version:i(/(?:sleipnir)[\s\/](\d+(?:\.\d+)+)/i)}:/k-meleon/i.test(e)?o={name:"K-Meleon",kMeleon:m,version:i(/(?:k-meleon)[\s\/](\d+(?:\.\d+)+)/i)}:h?(o={name:"Windows Phone",osname:"Windows Phone",windowsphone:m},k?(o.msedge=m,o.version=k):(o.msie=m,o.version=i(/iemobile\/(\d+(\.\d+)?)/i))):/msie|trident/i.test(e)?o={name:"Internet Explorer",msie:m,version:i(/(?:msie |rv:)(\d+(\.\d+)?)/i)}:p?o={name:"Chrome",osname:"Chrome OS",chromeos:m,chromeBook:m,chrome:m,version:i(/(?:chrome|crios|crmo)\/(\d+(\.\d+)?)/i)}:/chrome.+? edge/i.test(e)?o={name:"Microsoft Edge",msedge:m,version:k}:/vivaldi/i.test(e)?o={name:"Vivaldi",vivaldi:m,version:i(/vivaldi\/(\d+(\.\d+)?)/i)||x}:c?o={name:"Sailfish",osname:"Sailfish OS",sailfish:m,version:i(/sailfish\s?browser\/(\d+(\.\d+)?)/i)}:/seamonkey\//i.test(e)?o={name:"SeaMonkey",seamonkey:m,version:i(/seamonkey\/(\d+(\.\d+)?)/i)}:/firefox|iceweasel|fxios/i.test(e)?(o={name:"Firefox",firefox:m,version:i(/(?:firefox|iceweasel|fxios)[ \/](\d+(\.\d+)?)/i)},/\((mobile|tablet);[^\)]*rv:[\d\.]+\)/i.test(e)&&(o.firefoxos=m,o.osname="Firefox OS")):l?o={name:"Amazon Silk",silk:m,version:i(/silk\/(\d+(\.\d+)?)/i)}:/phantom/i.test(e)?o={name:"PhantomJS",phantom:m,version:i(/phantomjs\/(\d+(\.\d+)?)/i)}:/slimerjs/i.test(e)?o={name:"SlimerJS",slimer:m,version:i(/slimerjs\/(\d+(\.\d+)?)/i)}:/blackberry|\bbb\d+/i.test(e)||/rim\stablet/i.test(e)?o={name:"BlackBerry",osname:"BlackBerry OS",blackberry:m,version:x||i(/blackberry[\d]+\/(\d+(\.\d+)?)/i)}:f?(o={name:"WebOS",osname:"WebOS",webos:m,version:x||i(/w(?:eb)?osbrowser\/(\d+(\.\d+)?)/i)},/touchpad\//i.test(e)&&(o.touchpad=m)):/bada/i.test(e)?o={name:"Bada",osname:"Bada",bada:m,version:i(/dolfin\/(\d+(\.\d+)?)/i)}:u?o={name:"Tizen",osname:"Tizen",tizen:m,version:i(/(?:tizen\s?)?browser\/(\d+(\.\d+)?)/i)||x}:/qupzilla/i.test(e)?o={name:"QupZilla",qupzilla:m,version:i(/(?:qupzilla)[\s\/](\d+(?:\.\d+)+)/i)||x}:/chromium/i.test(e)?o={name:"Chromium",chromium:m,version:i(/(?:chromium)[\s\/](\d+(?:\.\d+)?)/i)||x}:/chrome|crios|crmo/i.test(e)?o={name:"Chrome",chrome:m,version:i(/(?:chrome|crios|crmo)\/(\d+(\.\d+)?)/i)}:a?o={name:"Android",version:x}:/safari|applewebkit/i.test(e)?(o={name:"Safari",safari:m},x&&(o.version=x)):n?(o={name:"iphone"==n?"iPhone":"ipad"==n?"iPad":"iPod"},x&&(o.version=x)):o=/googlebot/i.test(e)?{name:"Googlebot",googlebot:m,version:i(/googlebot\/(\d+(\.\d+))/i)||x}:{name:i(/^(.*)\/(.*) /),version:s(/^(.*)\/(.*) /)},!o.msedge&&/(apple)?webkit/i.test(e)?(/(apple)?webkit\/537\.36/i.test(e)?(o.name=o.name||"Blink",o.blink=m):(o.name=o.name||"Webkit",o.webkit=m),!o.version&&x&&(o.version=x)):!o.opera&&/gecko\//i.test(e)&&(o.name=o.name||"Gecko",o.gecko=m,o.version=o.version||i(/gecko\/(\d+(\.\d+)?)/i)),o.windowsphone||o.msedge||!a&&!o.silk?o.windowsphone||o.msedge||!n?b?(o.mac=m,o.osname="macOS"):B?(o.xbox=m,o.osname="Xbox"):w?(o.windows=m,o.osname="Windows"):g&&(o.linux=m,o.osname="Linux"):(o[n]=m,o.ios=m,o.osname="iOS"):(o.android=m,o.osname="Android");
var O="";o.windows?O=r(i(/Windows ((NT|XP)( \d\d?.\d)?)/i)):o.windowsphone?O=i(/windows phone (?:os)?\s?(\d+(\.\d+)*)/i):o.mac?(O=i(/Mac OS X (\d+([_\.\s]\d+)*)/i),O=O.replace(/[_\s]/g,".")):n?(O=i(/os (\d+([_\s]\d+)*) like mac os x/i),O=O.replace(/[_\s]/g,".")):a?O=i(/android[ \/-](\d+(\.\d+)*)/i):o.webos?O=i(/(?:web|hpw)os\/(\d+(\.\d+)*)/i):o.blackberry?O=i(/rim\stablet\sos\s(\d+(\.\d+)*)/i):o.bada?O=i(/bada\/(\d+(\.\d+)*)/i):o.tizen&&(O=i(/tizen[\/\s](\d+(\.\d+)*)/i)),O&&(o.osversion=O);
var T=!o.windows&&O.split(".")[0];return y||v||"ipad"==n||a&&(3==T||T>=4&&!S)||o.silk?o.tablet=m:(S||"iphone"==n||"ipod"==n||a||d||o.blackberry||o.webos||o.bada)&&(o.mobile=m),o.msedge||o.msie&&o.version>=10||o.yandexbrowser&&o.version>=15||o.vivaldi&&o.version>=1||o.chrome&&o.version>=20||o.samsungBrowser&&o.version>=4||o.firefox&&o.version>=20||o.safari&&o.version>=6||o.opera&&o.version>=10||o.ios&&o.osversion&&o.osversion.split(".")[0]>=6||o.blackberry&&o.version>=10.1||o.chromium&&o.version>=20?o.a=m:o.msie&&o.version<10||o.chrome&&o.version<20||o.firefox&&o.version<20||o.safari&&o.version<6||o.opera&&o.version<10||o.ios&&o.osversion&&o.osversion.split(".")[0]<6||o.chromium&&o.version<20?o.c=m:o.x=m,o
}function o(e){return e.split(".").length}function n(e,i){var s,r=[];if(Array.prototype.map)return Array.prototype.map.call(e,i);
for(s=0;s<e.length;s++)r.push(i(e[s]));return r}function t(e){for(var i=Math.max(o(e[0]),o(e[1])),s=n(e,function(e){var s=i-o(e);
return e+=new Array(s+1).join(".0"),n(e.split("."),function(e){return new Array(20-e.length).join("0")+e}).reverse()});--i>=0;){if(s[0][i]>s[1][i])return 1;
if(s[0][i]!==s[1][i])return-1;if(0===i)return 0}}function a(e,i,s){var o=v;"string"==typeof i&&(s=i,i=void 0),void 0===i&&(i=!1),s&&(o=r(s));
var n=""+o.version;for(var a in e)if(e.hasOwnProperty(a)&&o[a]){if("string"!=typeof e[a])throw new Error("Browser version in the minVersion map should be a string: "+a+": "+String(e));
return t([n,e[a]])<0}return i}function d(e,i,s){return!a(e,i,s)}var m=!0,v=r("undefined"!=typeof navigator?navigator.userAgent||"":"");
v.test=function(e){for(var i=0;i<e.length;++i){var s=e[i];if("string"==typeof s&&s in v)return!0}return!1},v.isUnsupportedBrowser=a,v.compareVersions=t,v.check=d,v._detect=r,s.exports=v
});
;define("translation:widget/common/third_party/clipboard",function(t,e,n){!function(t){if("object"==typeof e&&"undefined"!=typeof n)n.exports=t();
else if("function"==typeof define&&define.amd)define([],t);else{var o;o="undefined"!=typeof window?window:"undefined"!=typeof global?global:"undefined"!=typeof self?self:this,o.Clipboard=t()
}}(function(){var e;return function n(e,o,i){function r(c,s){if(!o[c]){if(!e[c]){var u="function"==typeof t&&t;if(!s&&u)return u(c,!0);
if(a)return a(c,!0);var l=new Error("Cannot find module '"+c+"'");throw l.code="MODULE_NOT_FOUND",l}var f=o[c]={exports:{}};
e[c][0].call(f.exports,function(t){var n=e[c][1][t];return r(n?n:t)},f,f.exports,n,e,o,i)}return o[c].exports}for(var a="function"==typeof t&&t,c=0;c<i.length;c++)r(i[c]);
return r}({1:[function(t,e){function n(t,e){for(;t&&t.nodeType!==o;){if("function"==typeof t.matches&&t.matches(e))return t;
t=t.parentNode}}var o=9;if("undefined"!=typeof Element&&!Element.prototype.matches){var i=Element.prototype;i.matches=i.matchesSelector||i.mozMatchesSelector||i.msMatchesSelector||i.oMatchesSelector||i.webkitMatchesSelector
}e.exports=n},{}],2:[function(t,e){function n(t,e,n,i,r){var a=o.apply(this,arguments);return t.addEventListener(n,a,r),{destroy:function(){t.removeEventListener(n,a,r)
}}}function o(t,e,n,o){return function(n){n.delegateTarget=i(n.target,e),n.delegateTarget&&o.call(t,n)}}var i=t("./closest");
e.exports=n},{"./closest":1}],3:[function(t,e,n){n.node=function(t){return void 0!==t&&t instanceof HTMLElement&&1===t.nodeType
},n.nodeList=function(t){var e=Object.prototype.toString.call(t);return void 0!==t&&("[object NodeList]"===e||"[object HTMLCollection]"===e)&&"length"in t&&(0===t.length||n.node(t[0]))
},n.string=function(t){return"string"==typeof t||t instanceof String},n.fn=function(t){var e=Object.prototype.toString.call(t);
return"[object Function]"===e}},{}],4:[function(t,e){function n(t,e,n){if(!t&&!e&&!n)throw new Error("Missing required arguments");
if(!a.string(e))throw new TypeError("Second argument must be a String");if(!a.fn(n))throw new TypeError("Third argument must be a Function");
if(a.node(t))return o(t,e,n);if(a.nodeList(t))return i(t,e,n);if(a.string(t))return r(t,e,n);throw new TypeError("First argument must be a String, HTMLElement, HTMLCollection, or NodeList")
}function o(t,e,n){return t.addEventListener(e,n),{destroy:function(){t.removeEventListener(e,n)}}}function i(t,e,n){return Array.prototype.forEach.call(t,function(t){t.addEventListener(e,n)
}),{destroy:function(){Array.prototype.forEach.call(t,function(t){t.removeEventListener(e,n)})}}}function r(t,e,n){return c(document.body,t,e,n)
}var a=t("./is"),c=t("delegate");e.exports=n},{"./is":3,delegate:2}],5:[function(t,e){function n(t){var e;if("SELECT"===t.nodeName)t.focus(),e=t.value;
else if("INPUT"===t.nodeName||"TEXTAREA"===t.nodeName){var n=t.hasAttribute("readonly");n||t.setAttribute("readonly",""),t.select(),t.setSelectionRange(0,t.value.length),n||t.removeAttribute("readonly"),e=t.value
}else{t.hasAttribute("contenteditable")&&t.focus();var o=window.getSelection(),i=document.createRange();i.selectNodeContents(t),o.removeAllRanges(),o.addRange(i),e=o.toString()
}return e}e.exports=n},{}],6:[function(t,e){function n(){}n.prototype={on:function(t,e,n){var o=this.e||(this.e={});return(o[t]||(o[t]=[])).push({fn:e,ctx:n}),this
},once:function(t,e,n){function o(){i.off(t,o),e.apply(n,arguments)}var i=this;return o._=e,this.on(t,o,n)},emit:function(t){var e=[].slice.call(arguments,1),n=((this.e||(this.e={}))[t]||[]).slice(),o=0,i=n.length;
for(o;i>o;o++)n[o].fn.apply(n[o].ctx,e);return this},off:function(t,e){var n=this.e||(this.e={}),o=n[t],i=[];if(o&&e)for(var r=0,a=o.length;a>r;r++)o[r].fn!==e&&o[r].fn._!==e&&i.push(o[r]);
return i.length?n[t]=i:delete n[t],this}},e.exports=n},{}],7:[function(t,n,o){!function(i,r){if("function"==typeof e&&e.amd)e(["module","select"],r);
else if("undefined"!=typeof o)r(n,t("select"));else{var a={exports:{}};r(a,i.select),i.clipboardAction=a.exports}}(this,function(t,e){"use strict";
function n(t){return t&&t.__esModule?t:{"default":t}}function o(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")
}var i=n(e),r="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(t){return typeof t}:function(t){return t&&"function"==typeof Symbol&&t.constructor===Symbol&&t!==Symbol.prototype?"symbol":typeof t
},a=function(){function t(t,e){for(var n=0;n<e.length;n++){var o=e[n];o.enumerable=o.enumerable||!1,o.configurable=!0,"value"in o&&(o.writable=!0),Object.defineProperty(t,o.key,o)
}}return function(e,n,o){return n&&t(e.prototype,n),o&&t(e,o),e}}(),c=function(){function t(e){o(this,t),this.resolveOptions(e),this.initSelection()
}return a(t,[{key:"resolveOptions",value:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};this.action=t.action,this.container=t.container,this.emitter=t.emitter,this.target=t.target,this.text=t.text,this.trigger=t.trigger,this.selectedText=""
}},{key:"initSelection",value:function(){this.text?this.selectFake():this.target&&this.selectTarget()}},{key:"selectFake",value:function(){var t=this,e="rtl"==document.documentElement.getAttribute("dir");
this.removeFake(),this.fakeHandlerCallback=function(){return t.removeFake()},this.fakeHandler=this.container.addEventListener("click",this.fakeHandlerCallback)||!0,this.fakeElem=document.createElement("textarea"),this.fakeElem.style.fontSize="12pt",this.fakeElem.style.border="0",this.fakeElem.style.padding="0",this.fakeElem.style.margin="0",this.fakeElem.style.position="absolute",this.fakeElem.style[e?"right":"left"]="-9999px";
var n=window.pageYOffset||document.documentElement.scrollTop;this.fakeElem.style.top=n+"px",this.fakeElem.setAttribute("readonly",""),this.fakeElem.value=this.text,this.container.appendChild(this.fakeElem),this.selectedText=i.default(this.fakeElem),this.copyText()
}},{key:"removeFake",value:function(){this.fakeHandler&&(this.container.removeEventListener("click",this.fakeHandlerCallback),this.fakeHandler=null,this.fakeHandlerCallback=null),this.fakeElem&&(this.container.removeChild(this.fakeElem),this.fakeElem=null)
}},{key:"selectTarget",value:function(){this.selectedText=i.default(this.target),this.copyText()}},{key:"copyText",value:function(){var t=void 0;
try{t=document.execCommand(this.action)}catch(e){t=!1}this.handleResult(t)}},{key:"handleResult",value:function(t){this.emitter.emit(t?"success":"error",{action:this.action,text:this.selectedText,trigger:this.trigger,clearSelection:this.clearSelection.bind(this),select:i.default})
}},{key:"clearSelection",value:function(){this.trigger&&this.trigger.focus(),window.getSelection().removeAllRanges()}},{key:"destroy",value:function(){this.removeFake()
}},{key:"action",set:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"copy";if(this._action=t,"copy"!==this._action&&"cut"!==this._action)throw new Error('Invalid "action" value, use either "copy" or "cut"')
},get:function(){return this._action}},{key:"target",set:function(t){if(void 0!==t){if(!t||"object"!==("undefined"==typeof t?"undefined":r(t))||1!==t.nodeType)throw new Error('Invalid "target" value, use a valid Element');
if("copy"===this.action&&t.hasAttribute("disabled"))throw new Error('Invalid "target" attribute. Please use "readonly" instead of "disabled" attribute');
if("cut"===this.action&&(t.hasAttribute("readonly")||t.hasAttribute("disabled")))throw new Error('Invalid "target" attribute. You can\'t cut text from elements with "readonly" or "disabled" attributes');
this._target=t}},get:function(){return this._target}}]),t}();t.exports=c})},{select:5}],8:[function(t,n,o){!function(i,r){if("function"==typeof e&&e.amd)e(["module","./clipboard-action","tiny-emitter","good-listener"],r);
else if("undefined"!=typeof o)r(n,t("./clipboard-action"),t("tiny-emitter"),t("good-listener"));else{var a={exports:{}};r(a,i.clipboardAction,i.tinyEmitter,i.goodListener),i.clipboard=a.exports
}}(this,function(t,e,n,o){"use strict";function i(t){return t&&t.__esModule?t:{"default":t}}function r(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")
}function a(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e
}function c(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);
t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)
}function s(t,e){var n="data-clipboard-"+t;if(e.hasAttribute(n))return e.getAttribute(n)}var u=i(e),l=i(n),f=i(o),d="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(t){return typeof t
}:function(t){return t&&"function"==typeof Symbol&&t.constructor===Symbol&&t!==Symbol.prototype?"symbol":typeof t},h=function(){function t(t,e){for(var n=0;n<e.length;n++){var o=e[n];
o.enumerable=o.enumerable||!1,o.configurable=!0,"value"in o&&(o.writable=!0),Object.defineProperty(t,o.key,o)}}return function(e,n,o){return n&&t(e.prototype,n),o&&t(e,o),e
}}(),p=function(t){function e(t,n){r(this,e);var o=a(this,(e.__proto__||Object.getPrototypeOf(e)).call(this));return o.resolveOptions(n),o.listenClick(t),o
}return c(e,t),h(e,[{key:"resolveOptions",value:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};
this.action="function"==typeof t.action?t.action:this.defaultAction,this.target="function"==typeof t.target?t.target:this.defaultTarget,this.text="function"==typeof t.text?t.text:this.defaultText,this.container="object"===d(t.container)?t.container:document.body
}},{key:"listenClick",value:function(t){var e=this;this.listener=f.default(t,"click",function(t){return e.onClick(t)})}},{key:"onClick",value:function(t){var e=t.delegateTarget||t.currentTarget;
this.clipboardAction&&(this.clipboardAction=null),this.clipboardAction=new u.default({action:this.action(e),target:this.target(e),text:this.text(e),container:this.container,trigger:e,emitter:this})
}},{key:"defaultAction",value:function(t){return s("action",t)}},{key:"defaultTarget",value:function(t){var e=s("target",t);
return e?document.querySelector(e):void 0}},{key:"defaultText",value:function(t){return s("text",t)}},{key:"destroy",value:function(){this.listener.destroy(),this.clipboardAction&&(this.clipboardAction.destroy(),this.clipboardAction=null)
}}],[{key:"isSupported",value:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:["copy","cut"],e="string"==typeof t?[t]:t,n=!!document.queryCommandSupported;
return e.forEach(function(t){n=n&&!!document.queryCommandSupported(t)}),n}}]),e}(l.default);t.exports=p})},{"./clipboard-action":7,"good-listener":4,"tiny-emitter":6}]},{},[8])(8)
})});
;define("translation:widget/common/third_party/image-compressor",function(t,e,r){"use strict";function n(t,e){return e={exports:{}},t(e,e.exports),e.exports
}function o(t){return v.test(t)}function i(t){var e=arguments.length>1&&void 0!==arguments[1]?arguments[1]:!0,r=o(t)?t.substr(6):"";
return"jpeg"===r&&(r="jpg"),r&&e&&(r="."+r),r}function a(t,e,r){var n="",o=void 0;for(r+=e,o=e;r>o;o+=1)n+=d(t.getUint8(o));
return n}function u(t,e){var r=new Uint8Array(t),n=r.length,o="",i=void 0;for(i=0;n>i;i+=1)o+=d(r[i]);return"data:"+e+";base64,"+b(o)
}function c(t){var e=new DataView(t),r=void 0,n=void 0,o=void 0,i=void 0;if(255===e.getUint8(0)&&216===e.getUint8(1))for(var u=e.byteLength,c=2;u>c;){if(255===e.getUint8(c)&&225===e.getUint8(c+1)){o=c;
break}c+=1}if(o){var s=o+4,l=o+10;if("Exif"===a(e,s,4)){var f=e.getUint16(l);if(n=18761===f,(n||19789===f)&&42===e.getUint16(l+2,n)){var h=e.getUint32(l+4,n);
h>=8&&(i=l+h)}}}if(i){var m=e.getUint16(i,n),v=void 0,d=void 0;for(d=0;m>d;d+=1)if(v=i+12*d+2,274===e.getUint16(v,n)){v+=8,r=e.getUint16(v,n),e.setUint16(v,1,n);
break}}return r}function s(t){var e=0,r=1,n=1;switch(t){case 2:r=-1;break;case 3:e=-180;break;case 4:n=-1;break;case 5:e=90,n=-1;
break;case 6:e=90;break;case 7:e=90,r=-1;break;case 8:e=-90}return{rotate:e,scaleX:r,scaleY:n}}var l=n(function(t){!function(e){var r=e.HTMLCanvasElement&&e.HTMLCanvasElement.prototype,n=e.Blob&&function(){try{return Boolean(new Blob)
}catch(t){return!1}}(),o=n&&e.Uint8Array&&function(){try{return 100===new Blob([new Uint8Array(100)]).size}catch(t){return!1
}}(),i=e.BlobBuilder||e.WebKitBlobBuilder||e.MozBlobBuilder||e.MSBlobBuilder,a=/^data:((.*?)(;charset=.*?)?)(;base64)?,/,u=(n||i)&&e.atob&&e.ArrayBuffer&&e.Uint8Array&&function(t){var e,r,u,c,s,l,f,h,m;
if(e=t.match(a),!e)throw new Error("invalid data URI");for(r=e[2]?e[1]:"text/plain"+(e[3]||";charset=US-ASCII"),u=!!e[4],c=t.slice(e[0].length),s=u?atob(c):decodeURIComponent(c),l=new ArrayBuffer(s.length),f=new Uint8Array(l),h=0;h<s.length;h+=1)f[h]=s.charCodeAt(h);
return n?new Blob([o?f:l],{type:r}):(m=new i,m.append(l),m.getBlob(r))};e.HTMLCanvasElement&&!r.toBlob&&(r.mozGetAsFile?r.toBlob=function(t,e,n){var o=this;
setTimeout(function(){t(n&&r.toDataURL&&u?u(o.toDataURL(e,n)):o.mozGetAsFile("blob",e))})}:r.toDataURL&&u&&(r.toBlob=function(t,e,r){var n=this;
setTimeout(function(){t(u(n.toDataURL(e,r)))})})),t.exports?t.exports=u:e.dataURLtoBlob=u}(window)}),f=Object.prototype.toString,h=function(t){return t instanceof Blob||"[object Blob]"===f.call(t)
},m={checkOrientation:!0,maxWidth:1/0,maxHeight:1/0,minWidth:0,minHeight:0,width:void 0,height:void 0,quality:.8,mimeType:"auto",convertSize:5e6,success:null,error:null},v=/^image\/.+$/,d=String.fromCharCode,g=window,b=g.btoa,p=(function(){function t(t){this.value=t
}function e(e){function r(t,e){return new Promise(function(r,o){var u={key:t,arg:e,resolve:r,reject:o,next:null};a?a=a.next=u:(i=a=u,n(t,e))
})}function n(r,i){try{var a=e[r](i),u=a.value;u instanceof t?Promise.resolve(u.value).then(function(t){n("next",t)},function(t){n("throw",t)
}):o(a.done?"return":"normal",a.value)}catch(c){o("throw",c)}}function o(t,e){switch(t){case"return":i.resolve({value:e,done:!0});
break;case"throw":i.reject(e);break;default:i.resolve({value:e,done:!1})}i=i.next,i?n(i.key,i.arg):a=null}var i,a;this._invoke=r,"function"!=typeof e.return&&(this.return=void 0)
}return"function"==typeof Symbol&&Symbol.asyncIterator&&(e.prototype[Symbol.asyncIterator]=function(){return this}),e.prototype.next=function(t){return this._invoke("next",t)
},e.prototype.throw=function(t){return this._invoke("throw",t)},e.prototype.return=function(t){return this._invoke("return",t)
},{wrap:function(t){return function(){return new e(t.apply(this,arguments))}},await:function(e){return new t(e)}}}(),function(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")
}),w=function(){function t(t,e){for(var r=0;r<e.length;r++){var n=e[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(t,n.key,n)
}}return function(e,r,n){return r&&t(e.prototype,r),n&&t(e,n),e}}(),y=Object.assign||function(t){for(var e=1;e<arguments.length;e++){var r=arguments[e];
for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(t[n]=r[n])}return t},U=window,B=U.ArrayBuffer,x=U.FileReader,k=window.URL||window.webkitURL,M=/\.\w+$/,T=function(){function t(e,r){p(this,t),this.result=null,e&&this.compress(e,r)
}return w(t,[{key:"compress",value:function(t,e){var r=this,n=new Image;return e=y({},m,e),B||(e.checkOrientation=!1),new Promise(function(r,n){if(!h(t))return void n(new Error("The first argument must be a File or Blob object."));
var i=t.type;if(!o(i))return void n(new Error("The first argument must be an image File or Blob object."));if(!k&&!x)return void n(new Error("The current browser does not support image compression."));
if(k&&!e.checkOrientation)r(k.createObjectURL(t));else if(x){var a=new x,l=e.checkOrientation&&"image/jpeg"===i;a.onload=function(t){var e=t.target,n=e.result;
r(l?y({url:u(n,i)},s(c(n))):{url:n})},a.onabort=n,a.onerror=n,l?a.readAsArrayBuffer(t):a.readAsDataURL(t)}}).then(function(e){return new Promise(function(r,o){n.onload=function(){return r(y({},e,{naturalWidth:n.naturalWidth,naturalHeight:n.naturalHeight}))
},n.onabort=o,n.onerror=o,n.alt=t.name,n.src=e.url})}).then(function(r){var i=r.naturalWidth,a=r.naturalHeight,u=r.rotate,c=void 0===u?0:u,s=r.scaleX,f=void 0===s?1:s,h=r.scaleY,m=void 0===h?1:h;
return new Promise(function(r){var u=document.createElement("canvas"),s=u.getContext("2d"),h=i/a,v=Math.max(e.maxWidth,0)||1/0,d=Math.max(e.maxHeight,0)||1/0,g=Math.max(e.minWidth,0)||0,b=Math.max(e.minHeight,0)||0,p=i,w=a;
if(1/0>v&&1/0>d?d*h>v?d=v/h:v=d*h:1/0>v?d=v/h:1/0>d&&(v=d*h),g>0&&b>0?b*h>g?b=g/h:g=b*h:g>0?b=g/h:b>0&&(g=b*h),e.width>0){var y=e;
p=y.width,w=p/h}else if(e.height>0){var U=e;w=U.height,p=w*h}p=Math.min(Math.max(p,g),v),w=Math.min(Math.max(w,b),d);var B=-p/2,x=-w/2,k=p,M=w;
if(Math.abs(c)%180===90){var T={width:w,height:p};p=T.width,w=T.height}u.width=p,u.height=w,s.fillStyle="transparent",s.fillRect(0,0,p,w),s.save(),s.translate(p/2,w/2),s.rotate(c*Math.PI/180),s.scale(f,m),s.drawImage(n,Math.floor(B),Math.floor(x),Math.floor(k),Math.floor(M)),s.restore(),o(e.mimeType)||(e.mimeType=t.type),t.size>e.convertSize&&"image/png"===e.mimeType&&(e.mimeType="image/jpeg"),u.toBlob?u.toBlob(r,e.mimeType,e.quality):r(l(u.toDataURL(e.mimeType,e.quality)))
})}).then(function(o){if(k&&setTimeout(function(){k.revokeObjectURL(n.src)},1e3),o)if(o.size>t.size&&!(e.width>0||e.height>0||e.maxWidth<1/0||e.maxHeight<1/0||e.minWidth>0||e.minHeight>0))o=t;
else{var a=new Date;o.lastModified=a.getTime(),o.lastModifiedDate=a,o.name=t.name,o.name&&o.type!==t.type&&(o.name=o.name.replace(M,i(o.type)))
}else o=t;return r.result=o,e.success&&e.success(o),Promise.resolve(o)}).catch(function(t){if(!e.error)throw t;e.error(t)
})}}]),t}();r.exports=T});
;define("translation:widget/common/third_party/jquery_scrollbar/jquery_scrollbar",function(){"use strict";function l(l){if(r.webkit&&!l)return{height:0,width:0};
if(!r.data.outer){var e={border:"none","box-sizing":"content-box",height:"200px",margin:"0",padding:"0",width:"200px"};r.data.inner=$("<div>").css($.extend({},e)),r.data.outer=$("<div>").css($.extend({left:"-1000px",overflow:"scroll",position:"absolute",top:"-1000px"},e)).append(r.data.inner).appendTo("body")
}return r.data.outer.scrollLeft(1e3).scrollTop(1e3),{height:Math.ceil(r.data.outer.offset().top-r.data.inner.offset().top||0),width:Math.ceil(r.data.outer.offset().left-r.data.inner.offset().left||0)}
}function e(){var e=l(!0);return!(e.height||e.width)}function s(l){var e=l.originalEvent;return e.axis&&e.axis===e.HORIZONTAL_AXIS?!1:e.wheelDeltaX?!1:!0
}var o=!1,r={data:{index:0,name:"scrollbar"},firefox:/firefox/i.test(navigator.userAgent),macosx:/mac/i.test(navigator.platform),msedge:/edge\/\d+/i.test(navigator.userAgent),msie:/(msie|trident)/i.test(navigator.userAgent),mobile:/android|webos|iphone|ipad|ipod|blackberry/i.test(navigator.userAgent),overlay:null,scroll:null,scrolls:[],webkit:/webkit/i.test(navigator.userAgent)&&!/edge\/\d+/i.test(navigator.userAgent)};
r.scrolls.add=function(l){this.remove(l).push(l)},r.scrolls.remove=function(l){for(;$.inArray(l,this)>=0;)this.splice($.inArray(l,this),1);
return this};var t={autoScrollSize:!0,autoUpdate:!0,debug:!1,disableBodyScroll:!1,duration:200,ignoreMobile:!1,ignoreOverlay:!1,isRtl:!1,scrollStep:30,showArrows:!1,stepScrolling:!0,scrollx:null,scrolly:null,onDestroy:null,onFallback:null,onInit:null,onScroll:null,onUpdate:null},i=function(s){r.scroll||(r.overlay=e(),r.scroll=l(),a(),$(window).resize(function(){var e=!1;
if(r.scroll&&(r.scroll.height||r.scroll.width)){var s=l();(s.height!==r.scroll.height||s.width!==r.scroll.width)&&(r.scroll=s,e=!0)
}a(e)})),this.container=s,this.namespace=".scrollbar_"+r.data.index++,this.options=$.extend({},t,window.jQueryScrollbarOptions||{}),this.scrollTo=null,this.scrollx={},this.scrolly={},s.data(r.data.name,this),r.scrolls.add(this)
};i.prototype={destroy:function(){if(this.wrapper){this.container.removeData(r.data.name),r.scrolls.remove(this);var l=this.container.scrollLeft(),e=this.container.scrollTop();
this.container.insertBefore(this.wrapper).css({height:"",margin:"","max-height":""}).removeClass("scroll-content scroll-scrollx_visible scroll-scrolly_visible").off(this.namespace).scrollLeft(l).scrollTop(e),this.scrollx.scroll.removeClass("scroll-scrollx_visible").find("div").addBack().off(this.namespace),this.scrolly.scroll.removeClass("scroll-scrolly_visible").find("div").addBack().off(this.namespace),this.wrapper.remove(),$(document).add("body").off(this.namespace),$.isFunction(this.options.onDestroy)&&this.options.onDestroy.apply(this,[this.container])
}},init:function(l){var e=this,o=this.container,t=this.containerWrapper||o,i=this.namespace,n=$.extend(this.options,l||{}),a={x:this.scrollx,y:this.scrolly},c=this.wrapper,d={},h={scrollLeft:o.scrollLeft(),scrollTop:o.scrollTop()};
if(r.mobile&&n.ignoreMobile||r.overlay&&n.ignoreOverlay||r.macosx&&!r.webkit)return $.isFunction(n.onFallback)&&n.onFallback.apply(this,[o]),!1;
if(c)d={height:"auto","margin-bottom":-1*r.scroll.height+"px","max-height":""},d[n.isRtl?"margin-left":"margin-right"]=-1*r.scroll.width+"px",t.css(d);
else{if(this.wrapper=c=$("<div>").addClass("scroll-wrapper").addClass(o.attr("class")).css("position","absolute"===o.css("position")?"absolute":"relative").insertBefore(o).append(o),n.isRtl&&c.addClass("scroll--rtl"),o.is("textarea")&&(this.containerWrapper=t=$("<div>").insertBefore(o).append(o),c.addClass("scroll-textarea")),d={height:"auto","margin-bottom":-1*r.scroll.height+"px","max-height":""},d[n.isRtl?"margin-left":"margin-right"]=-1*r.scroll.width+"px",t.addClass("scroll-content").css(d),o.on("scroll"+i,function(){var l=o.scrollLeft(),s=o.scrollTop();
if(n.isRtl)switch(!0){case r.firefox:l=Math.abs(l);case r.msedge||r.msie:l=o[0].scrollWidth-o[0].clientWidth-l}$.isFunction(n.onScroll)&&n.onScroll.call(e,{maxScroll:a.y.maxScrollOffset,scroll:s,size:a.y.size,visible:a.y.visible},{maxScroll:a.x.maxScrollOffset,scroll:l,size:a.x.size,visible:a.x.visible}),a.x.isVisible&&a.x.scroll.bar.css("left",l*a.x.kx+"px"),a.y.isVisible&&a.y.scroll.bar.css("top",s*a.y.kx+"px")
}),c.on("scroll"+i,function(){c.scrollTop(0).scrollLeft(0)}),n.disableBodyScroll){var u=function(l){s(l)?a.y.isVisible&&a.y.mousewheel(l):a.x.isVisible&&a.x.mousewheel(l)
};c.on("MozMousePixelScroll"+i,u),c.on("mousewheel"+i,u),r.mobile&&c.on("touchstart"+i,function(l){var e=l.originalEvent.touches&&l.originalEvent.touches[0]||l,s={pageX:e.pageX,pageY:e.pageY},r={left:o.scrollLeft(),top:o.scrollTop()};
$(document).on("touchmove"+i,function(l){var e=l.originalEvent.targetTouches&&l.originalEvent.targetTouches[0]||l;o.scrollLeft(r.left+s.pageX-e.pageX),o.scrollTop(r.top+s.pageY-e.pageY),l.preventDefault()
}),$(document).on("touchend"+i,function(){$(document).off(i)})})}$.isFunction(n.onInit)&&n.onInit.apply(this,[o])}$.each(a,function(l,t){var c=null,d=1,h="x"===l?"scrollLeft":"scrollTop",u=n.scrollStep,p=function(){var l=o[h]();
o[h](l+u),1==d&&l+u>=f&&(l=o[h]()),-1==d&&f>=l+u&&(l=o[h]()),o[h]()==l&&c&&c()},f=0;t.scroll||(t.scroll=e._getScroll(n["scroll"+l]).addClass("scroll-"+l),n.showArrows&&t.scroll.addClass("scroll-element_arrows_visible"),t.mousewheel=function(r){if(!t.isVisible||"x"===l&&s(r))return!0;
if("y"===l&&!s(r))return a.x.mousewheel(r),!0;var i=-1*r.originalEvent.wheelDelta||r.originalEvent.detail,n=t.size-t.visible-t.offset;
return i||("x"===l&&r.originalEvent.deltaX?i=40*r.originalEvent.deltaX:"y"===l&&r.originalEvent.deltaY&&(i=40*r.originalEvent.deltaY)),(i>0&&n>f||0>i&&f>0)&&(f+=i,0>f&&(f=0),f>n&&(f=n),e.scrollTo=e.scrollTo||{},e.scrollTo[h]=f,setTimeout(function(){e.scrollTo&&(o.stop().animate(e.scrollTo,240,"linear",function(){f=o[h]()
}),e.scrollTo=null)},1)),r.preventDefault(),!1},t.scroll.on("MozMousePixelScroll"+i,t.mousewheel).on("mousewheel"+i,t.mousewheel).on("mouseenter"+i,function(){f=o[h]()
}),t.scroll.find(".scroll-arrow, .scroll-element_track").on("mousedown"+i,function(s){if(1!=s.which)return!0;d=1;var i={eventOffset:s["x"===l?"pageX":"pageY"],maxScrollValue:t.size-t.visible-t.offset,scrollbarOffset:t.scroll.bar.offset()["x"===l?"left":"top"],scrollbarSize:t.scroll.bar["x"===l?"outerWidth":"outerHeight"]()},a=0,v=0;
if($(this).hasClass("scroll-arrow")){if(d=$(this).hasClass("scroll-arrow_more")?1:-1,u=n.scrollStep*d,f=d>0?i.maxScrollValue:0,n.isRtl)switch(!0){case r.firefox:f=d>0?0:-1*i.maxScrollValue;
break;case r.msie||r.msedge:}}else d=i.eventOffset>i.scrollbarOffset+i.scrollbarSize?1:i.eventOffset<i.scrollbarOffset?-1:0,"x"===l&&n.isRtl&&(r.msie||r.msedge)&&(d=-1*d),u=Math.round(.75*t.visible)*d,f=i.eventOffset-i.scrollbarOffset-(n.stepScrolling?1==d?i.scrollbarSize:0:Math.round(i.scrollbarSize/2)),f=o[h]()+f/t.kx;
return e.scrollTo=e.scrollTo||{},e.scrollTo[h]=n.stepScrolling?o[h]()+u:f,n.stepScrolling&&(c=function(){f=o[h](),clearInterval(v),clearTimeout(a),a=0,v=0
},a=setTimeout(function(){v=setInterval(p,40)},n.duration+100)),setTimeout(function(){e.scrollTo&&(o.animate(e.scrollTo,n.duration),e.scrollTo=null)
},1),e._handleMouseDown(c,s)}),t.scroll.bar.on("mousedown"+i,function(s){if(1!=s.which)return!0;var a=s["x"===l?"pageX":"pageY"],c=o[h]();
return t.scroll.addClass("scroll-draggable"),$(document).on("mousemove"+i,function(e){var s=parseInt((e["x"===l?"pageX":"pageY"]-a)/t.kx,10);
"x"===l&&n.isRtl&&(r.msie||r.msedge)&&(s=-1*s),o[h](c+s)}),e._handleMouseDown(function(){t.scroll.removeClass("scroll-draggable"),f=o[h]()
},s)}))}),$.each(a,function(l,e){var s="scroll-scroll"+l+"_visible",o="x"==l?a.y:a.x;e.scroll.removeClass(s),o.scroll.removeClass(s),t.removeClass(s)
}),$.each(a,function(l,e){$.extend(e,"x"==l?{offset:parseInt(o.css("left"),10)||0,size:o.prop("scrollWidth"),visible:c.width()}:{offset:parseInt(o.css("top"),10)||0,size:o.prop("scrollHeight"),visible:c.height()})
}),this._updateScroll("x",this.scrollx),this._updateScroll("y",this.scrolly),$.isFunction(n.onUpdate)&&n.onUpdate.apply(this,[o]),$.each(a,function(l,e){var s="x"===l?"left":"top",r="x"===l?"outerWidth":"outerHeight",t="x"===l?"width":"height",i=parseInt(o.css(s),10)||0,a=e.size,c=e.visible+i,d=e.scroll.size[r]()+(parseInt(e.scroll.size.css(s),10)||0);
n.autoScrollSize&&(e.scrollbarSize=parseInt(d*c/a,10),e.scroll.bar.css(t,e.scrollbarSize+"px")),e.scrollbarSize=e.scroll.bar[r](),e.kx=(d-e.scrollbarSize)/(a-c)||1,e.maxScrollOffset=a-c
}),o.scrollLeft(h.scrollLeft).scrollTop(h.scrollTop).trigger("scroll")},_getScroll:function(l){var e={advanced:['<div class="scroll-element">','<div class="scroll-element_corner"></div>','<div class="scroll-arrow scroll-arrow_less"></div>','<div class="scroll-arrow scroll-arrow_more"></div>','<div class="scroll-element_outer">','<div class="scroll-element_size"></div>','<div class="scroll-element_inner-wrapper">','<div class="scroll-element_inner scroll-element_track">','<div class="scroll-element_inner-bottom"></div>',"</div>","</div>",'<div class="scroll-bar">','<div class="scroll-bar_body">','<div class="scroll-bar_body-inner"></div>',"</div>",'<div class="scroll-bar_bottom"></div>','<div class="scroll-bar_center"></div>',"</div>","</div>","</div>"].join(""),simple:['<div class="scroll-element">','<div class="scroll-element_outer">','<div class="scroll-element_size"></div>','<div class="scroll-element_track"></div>','<div class="scroll-bar"></div>',"</div>","</div>"].join("")};
return e[l]&&(l=e[l]),l||(l=e.simple),l="string"==typeof l?$(l).appendTo(this.wrapper):$(l),$.extend(l,{bar:l.find(".scroll-bar"),size:l.find(".scroll-element_size"),track:l.find(".scroll-element_track")}),l
},_handleMouseDown:function(l,e){var s=this.namespace;return $(document).on("blur"+s,function(){$(document).add("body").off(s),l&&l()
}),$(document).on("dragstart"+s,function(l){return l.preventDefault(),!1}),$(document).on("mouseup"+s,function(){$(document).add("body").off(s),l&&l()
}),$("body").on("selectstart"+s,function(l){return l.preventDefault(),!1}),e&&e.preventDefault(),!1},_updateScroll:function(l,e){var s=this.container,o=this.containerWrapper||s,t="scroll-scroll"+l+"_visible",i="x"===l?this.scrolly:this.scrollx,n=parseInt(this.container.css("x"===l?"left":"top"),10)||0,a=this.wrapper,c=e.size,d=e.visible+n;
e.isVisible=c-d>1,e.isVisible?(e.scroll.addClass(t),i.scroll.addClass(t),o.addClass(t)):(e.scroll.removeClass(t),i.scroll.removeClass(t),o.removeClass(t)),"y"===l&&o.css(s.is("textarea")||d>c?{height:d+r.scroll.height+"px","max-height":"none"}:{"max-height":d+r.scroll.height+"px"}),(e.size!=s.prop("scrollWidth")||i.size!=s.prop("scrollHeight")||e.visible!=a.width()||i.visible!=a.height()||e.offset!=(parseInt(s.css("left"),10)||0)||i.offset!=(parseInt(s.css("top"),10)||0))&&($.extend(this.scrollx,{offset:parseInt(s.css("left"),10)||0,size:s.prop("scrollWidth"),visible:a.width()}),$.extend(this.scrolly,{offset:parseInt(s.css("top"),10)||0,size:this.container.prop("scrollHeight"),visible:a.height()}),this._updateScroll("x"===l?"y":"x",i))
}};var n=i;$.fn.scrollbar=function(l,e){return"string"!=typeof l&&(e=l,l="init"),"undefined"==typeof e&&(e=[]),$.isArray(e)||(e=[e]),this.not("body, .scroll-wrapper").each(function(){var s=$(this),o=s.data(r.data.name);
(o||"init"===l)&&(o||(o=new n(s)),o[l]&&o[l].apply(o,e))}),this},$.fn.scrollbar.options=t;var a=function(){var l=0,e=0;return function(s){var t,i,n,c,d,h,u;
for(t=0;t<r.scrolls.length;t++)c=r.scrolls[t],i=c.container,n=c.options,d=c.wrapper,h=c.scrollx,u=c.scrolly,(s||n.autoUpdate&&d&&d.is(":visible")&&(i.prop("scrollWidth")!=h.size||i.prop("scrollHeight")!=u.size||d.width()!=h.visible||d.height()!=u.visible))&&(c.init(),n.debug&&(window.console&&console.log({scrollHeight:i.prop("scrollHeight")+":"+c.scrolly.size,scrollWidth:i.prop("scrollWidth")+":"+c.scrollx.size,visibleHeight:d.height()+":"+c.scrolly.visible,visibleWidth:d.width()+":"+c.scrollx.visible},!0),e++));
o&&e>10?(window.console&&console.log("Scroll updates exceed 10"),a=function(){}):(clearTimeout(l),l=setTimeout(a,300))}}();
window.angular&&!function(l){l.module("jQueryScrollbar",[]).provider("jQueryScrollbar",function(){var e=t;return{setOptions:function(s){l.extend(e,s)
},$get:function(){return{options:l.copy(e)}}}}).directive("jqueryScrollbar",["jQueryScrollbar","$parse",function(l,e){return{restrict:"AC",link:function(s,o,r){var t=e(r.jqueryScrollbar),i=t(s);
o.scrollbar(i||l.options).on("$destroy",function(){o.scrollbar("destroy")})}}}])}(window.angular)});
;define("translation:widget/common/third_party/jquery.placeholder",function(){function e(e){var a={},t=/^jQuery\d+$/;return $.each(e.attributes,function(e,l){l.specified&&!t.test(l.name)&&(a[l.name]=l.value)
}),a}function a(e,a){var t=this,r=$(this);if(t.value===r.attr(d?"placeholder-x":"placeholder")&&r.hasClass(p.customClass))if(t.value="",r.removeClass(p.customClass),r.data("placeholder-password")){if(r=r.hide().nextAll('input[type="password"]:first').show().attr("id",r.removeAttr("id").data("placeholder-id")),e===!0)return r[0].value=a,a;
r.focus()}else t==l()&&t.select()}function t(t){var l,r=this,o=$(this),s=r.id;if(!t||"blur"!==t.type||!o.hasClass(p.customClass))if(""===r.value){if("password"===r.type){if(!o.data("placeholder-textinput")){try{l=o.clone().prop({type:"text"})
}catch(c){l=$("<input>").attr($.extend(e(this),{type:"text"}))}l.removeAttr("name").data({"placeholder-enabled":!0,"placeholder-password":o,"placeholder-id":s}).bind("focus.placeholder",a),o.data({"placeholder-textinput":l,"placeholder-id":s}).before(l)
}r.value="",o=o.removeAttr("id").hide().prevAll('input[type="text"]:first').attr("id",o.data("placeholder-id")).show()}else{var n=o.data("placeholder-password");
n&&(n[0].value="",o.attr("id",o.data("placeholder-id")).show().nextAll('input[type="password"]:last').hide().removeAttr("id"))
}o.addClass(p.customClass),o[0].value=o.attr(d?"placeholder-x":"placeholder")}else o.removeClass(p.customClass)}function l(){try{return document.activeElement
}catch(e){}}var r,o,d=!1,s="[object OperaMini]"===Object.prototype.toString.call(window.operamini),c="placeholder"in document.createElement("input")&&!s&&!d,n="placeholder"in document.createElement("textarea")&&!s&&!d,i=$.valHooks,u=$.propHooks,p={};
c&&n?(o=$.fn.placeholder=function(){return this},o.input=!0,o.textarea=!0):(o=$.fn.placeholder=function(e){var l={customClass:"placeholder"};
return p=$.extend({},l,e),this.filter((c?"textarea":":input")+"["+(d?"placeholder-x":"placeholder")+"]").not("."+p.customClass).not(":radio, :checkbox, [type=hidden]").bind({"focus.placeholder":a,"blur.placeholder":t}).data("placeholder-enabled",!0).trigger("blur.placeholder")
},o.input=c,o.textarea=n,r={get:function(e){var a=$(e),t=a.data("placeholder-password");return t?t[0].value:a.data("placeholder-enabled")&&a.hasClass(p.customClass)?"":e.value
},set:function(e,r){var o,d,s=$(e);return""!==r&&(o=s.data("placeholder-textinput"),d=s.data("placeholder-password"),o?(a.call(o[0],!0,r)||(e.value=r),o[0].value=r):d&&(a.call(e,!0,r)||(d[0].value=r),e.value=r)),s.data("placeholder-enabled")?(""===r?(e.value=r,e!=l()&&t.call(e)):(s.hasClass(p.customClass)&&a.call(e),e.value=r),s):(e.value=r,s)
}},c||(i.input=r,u.value=r),n||(i.textarea=r,u.value=r),$(function(){$(document).delegate("form","submit.placeholder",function(){var e=$("."+p.customClass,this).each(function(){a.call(this,!0,"")
});setTimeout(function(){e.each(t)},10)})}),$(window).bind("beforeunload.placeholder",function(){var e=!0;try{"javascript:void(0)"===document.activeElement.toString()&&(e=!1)
}catch(a){}e&&$("."+p.customClass).each(function(){this.value=""})}))});
;define("translation:widget/common/third_party/json2",function(require,exports,module){"object"!=typeof JSON&&(JSON={}),function(){"use strict";
function f(t){return 10>t?"0"+t:t}function this_value(){return this.valueOf()}function quote(t){return rx_escapable.lastIndex=0,rx_escapable.test(t)?'"'+t.replace(rx_escapable,function(t){var e=meta[t];
return"string"==typeof e?e:"\\u"+("0000"+t.charCodeAt(0).toString(16)).slice(-4)})+'"':'"'+t+'"'}function str(t,e){var r,n,o,u,f,i=gap,a=e[t];
switch(a&&"object"==typeof a&&"function"==typeof a.toJSON&&(a=a.toJSON(t)),"function"==typeof rep&&(a=rep.call(e,t,a)),typeof a){case"string":return quote(a);
case"number":return isFinite(a)?String(a):"null";case"boolean":case"null":return String(a);case"object":if(!a)return"null";
if(gap+=indent,f=[],"[object Array]"===Object.prototype.toString.apply(a)){for(u=a.length,r=0;u>r;r+=1)f[r]=str(r,a)||"null";
return o=0===f.length?"[]":gap?"[\n"+gap+f.join(",\n"+gap)+"\n"+i+"]":"["+f.join(",")+"]",gap=i,o}if(rep&&"object"==typeof rep)for(u=rep.length,r=0;u>r;r+=1)"string"==typeof rep[r]&&(n=rep[r],o=str(n,a),o&&f.push(quote(n)+(gap?": ":":")+o));
else for(n in a)Object.prototype.hasOwnProperty.call(a,n)&&(o=str(n,a),o&&f.push(quote(n)+(gap?": ":":")+o));return o=0===f.length?"{}":gap?"{\n"+gap+f.join(",\n"+gap)+"\n"+i+"}":"{"+f.join(",")+"}",gap=i,o
}}var rx_one=/^[\],:{}\s]*$/,rx_two=/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,rx_three=/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,rx_four=/(?:^|:|,)(?:\s*\[)+/g,rx_escapable=/[\\\"\u0000-\u001f\u007f-\u009f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,rx_dangerous=/[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
"function"!=typeof Date.prototype.toJSON&&(Date.prototype.toJSON=function(){return isFinite(this.valueOf())?this.getUTCFullYear()+"-"+f(this.getUTCMonth()+1)+"-"+f(this.getUTCDate())+"T"+f(this.getUTCHours())+":"+f(this.getUTCMinutes())+":"+f(this.getUTCSeconds())+"Z":null
},Boolean.prototype.toJSON=this_value,Number.prototype.toJSON=this_value,String.prototype.toJSON=this_value);var gap,indent,meta,rep;
"function"!=typeof JSON.stringify&&(meta={"\b":"\\b","	":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"},JSON.stringify=function(t,e,r){var n;
if(gap="",indent="","number"==typeof r)for(n=0;r>n;n+=1)indent+=" ";else"string"==typeof r&&(indent=r);if(rep=e,e&&"function"!=typeof e&&("object"!=typeof e||"number"!=typeof e.length))throw new Error("JSON.stringify");
return str("",{"":t})}),"function"!=typeof JSON.parse&&(JSON.parse=function(text,reviver){function walk(t,e){var r,n,o=t[e];
if(o&&"object"==typeof o)for(r in o)Object.prototype.hasOwnProperty.call(o,r)&&(n=walk(o,r),void 0!==n?o[r]=n:delete o[r]);
return reviver.call(t,e,o)}var j;if(text=String(text),rx_dangerous.lastIndex=0,rx_dangerous.test(text)&&(text=text.replace(rx_dangerous,function(t){return"\\u"+("0000"+t.charCodeAt(0).toString(16)).slice(-4)
})),rx_one.test(text.replace(rx_two,"@").replace(rx_three,"]").replace(rx_four,"")))return j=eval("("+text+")"),"function"==typeof reviver?walk({"":j},""):j;
throw new SyntaxError("JSON.parse")})}()});
;define("translation:widget/common/third_party/md5",function(n,t,r){function e(n,t){var r=(65535&n)+(65535&t),e=(n>>16)+(t>>16)+(r>>16);
return e<<16|65535&r}function o(n,t){return n<<t|n>>>32-t}function u(n,t,r,u,c,f){return e(o(e(e(t,n),e(u,f)),c),r)}function c(n,t,r,e,o,c,f){return u(t&r|~t&e,n,t,o,c,f)
}function f(n,t,r,e,o,c,f){return u(t&e|r&~e,n,t,o,c,f)}function i(n,t,r,e,o,c,f){return u(t^r^e,n,t,o,c,f)}function a(n,t,r,e,o,c,f){return u(r^(t|~e),n,t,o,c,f)
}function h(n,t){n[t>>5]|=128<<t%32,n[(t+64>>>9<<4)+14]=t;var r,o,u,h,g,d=1732584193,l=-271733879,v=-1732584194,m=271733878;
for(r=0;r<n.length;r+=16)o=d,u=l,h=v,g=m,d=c(d,l,v,m,n[r],7,-680876936),m=c(m,d,l,v,n[r+1],12,-389564586),v=c(v,m,d,l,n[r+2],17,606105819),l=c(l,v,m,d,n[r+3],22,-1044525330),d=c(d,l,v,m,n[r+4],7,-176418897),m=c(m,d,l,v,n[r+5],12,1200080426),v=c(v,m,d,l,n[r+6],17,-1473231341),l=c(l,v,m,d,n[r+7],22,-45705983),d=c(d,l,v,m,n[r+8],7,1770035416),m=c(m,d,l,v,n[r+9],12,-1958414417),v=c(v,m,d,l,n[r+10],17,-42063),l=c(l,v,m,d,n[r+11],22,-1990404162),d=c(d,l,v,m,n[r+12],7,1804603682),m=c(m,d,l,v,n[r+13],12,-40341101),v=c(v,m,d,l,n[r+14],17,-1502002290),l=c(l,v,m,d,n[r+15],22,1236535329),d=f(d,l,v,m,n[r+1],5,-165796510),m=f(m,d,l,v,n[r+6],9,-1069501632),v=f(v,m,d,l,n[r+11],14,643717713),l=f(l,v,m,d,n[r],20,-373897302),d=f(d,l,v,m,n[r+5],5,-701558691),m=f(m,d,l,v,n[r+10],9,38016083),v=f(v,m,d,l,n[r+15],14,-660478335),l=f(l,v,m,d,n[r+4],20,-405537848),d=f(d,l,v,m,n[r+9],5,568446438),m=f(m,d,l,v,n[r+14],9,-1019803690),v=f(v,m,d,l,n[r+3],14,-187363961),l=f(l,v,m,d,n[r+8],20,1163531501),d=f(d,l,v,m,n[r+13],5,-1444681467),m=f(m,d,l,v,n[r+2],9,-51403784),v=f(v,m,d,l,n[r+7],14,1735328473),l=f(l,v,m,d,n[r+12],20,-1926607734),d=i(d,l,v,m,n[r+5],4,-378558),m=i(m,d,l,v,n[r+8],11,-2022574463),v=i(v,m,d,l,n[r+11],16,1839030562),l=i(l,v,m,d,n[r+14],23,-35309556),d=i(d,l,v,m,n[r+1],4,-1530992060),m=i(m,d,l,v,n[r+4],11,1272893353),v=i(v,m,d,l,n[r+7],16,-155497632),l=i(l,v,m,d,n[r+10],23,-1094730640),d=i(d,l,v,m,n[r+13],4,681279174),m=i(m,d,l,v,n[r],11,-358537222),v=i(v,m,d,l,n[r+3],16,-722521979),l=i(l,v,m,d,n[r+6],23,76029189),d=i(d,l,v,m,n[r+9],4,-640364487),m=i(m,d,l,v,n[r+12],11,-421815835),v=i(v,m,d,l,n[r+15],16,530742520),l=i(l,v,m,d,n[r+2],23,-995338651),d=a(d,l,v,m,n[r],6,-198630844),m=a(m,d,l,v,n[r+7],10,1126891415),v=a(v,m,d,l,n[r+14],15,-1416354905),l=a(l,v,m,d,n[r+5],21,-57434055),d=a(d,l,v,m,n[r+12],6,1700485571),m=a(m,d,l,v,n[r+3],10,-1894986606),v=a(v,m,d,l,n[r+10],15,-1051523),l=a(l,v,m,d,n[r+1],21,-2054922799),d=a(d,l,v,m,n[r+8],6,1873313359),m=a(m,d,l,v,n[r+15],10,-30611744),v=a(v,m,d,l,n[r+6],15,-1560198380),l=a(l,v,m,d,n[r+13],21,1309151649),d=a(d,l,v,m,n[r+4],6,-145523070),m=a(m,d,l,v,n[r+11],10,-1120210379),v=a(v,m,d,l,n[r+2],15,718787259),l=a(l,v,m,d,n[r+9],21,-343485551),d=e(d,o),l=e(l,u),v=e(v,h),m=e(m,g);
return[d,l,v,m]}function g(n){var t,r="",e=32*n.length;for(t=0;e>t;t+=8)r+=String.fromCharCode(n[t>>5]>>>t%32&255);return r
}function d(n){var t,r=[];for(r[(n.length>>2)-1]=void 0,t=0;t<r.length;t+=1)r[t]=0;var e=8*n.length;for(t=0;e>t;t+=8)r[t>>5]|=(255&n.charCodeAt(t/8))<<t%32;
return r}function l(n){return g(h(d(n),8*n.length))}function v(n,t){var r,e,o=d(n),u=[],c=[];for(u[15]=c[15]=void 0,o.length>16&&(o=h(o,8*n.length)),r=0;16>r;r+=1)u[r]=909522486^o[r],c[r]=1549556828^o[r];
return e=h(u.concat(d(t)),512+8*t.length),g(h(c.concat(e),640))}function m(n){var t,r,e="0123456789abcdef",o="";for(r=0;r<n.length;r+=1)t=n.charCodeAt(r),o+=e.charAt(t>>>4&15)+e.charAt(15&t);
return o}function C(n){return unescape(encodeURIComponent(n))}function p(n){return l(C(n))}function A(n){return m(p(n))}function s(n,t){return v(C(n),C(t))
}function b(n,t){return m(s(n,t))}function w(n,t,r){return t?r?s(t,n):b(t,n):r?p(n):A(n)}r.exports=w});
;define("translation:widget/common/third_party/template",function(e,n,r){!function(){function e(e){return e.replace(b,"").replace(w,",").replace(x,"").replace(j,"").replace(T,"").split(k)
}function n(e){return"'"+e.replace(/('|\\)/g,"\\$1").replace(/\r/g,"\\r").replace(/\n/g,"\\n")+"'"}function t(r,t){function a(e){return p+=e.split(/\n/).length-1,u&&(e=e.replace(/\s+/g," ").replace(/<!--[\w\W]*?-->/g,"")),e&&(e=m[1]+n(e)+m[2]+"\n"),e
}function i(n){var r=p;if(s?n=s(n,t):o&&(n=n.replace(/\n/g,function(){return p++,"$line="+p+";"})),0===n.indexOf("=")){var a=f&&!/^=[=#]/.test(n);
if(n=n.replace(/^=[=#]?|[\s;]*$/g,""),a){var i=n.replace(/\s*\([^\)]+\)/,"");g[i]||/^(include|print)$/.test(i)||(n="$escape("+n+")")
}else n="$string("+n+")";n=m[1]+n+m[2]}return o&&(n="$line="+r+";"+n),v(e(n),function(e){if(e&&!$[e]){var n;n="print"===e?b:"include"===e?w:g[e]?"$utils."+e:d[e]?"$helpers."+e:"$data."+e,x+=e+"="+n+",",$[e]=!0
}}),n+"\n"}var o=t.debug,c=t.openTag,l=t.closeTag,s=t.parser,u=t.compress,f=t.escape,p=1,$={$data:1,$filename:1,$utils:1,$helpers:1,$out:1,$line:1},h="".trim,m=h?["$out='';","$out+=",";","$out"]:["$out=[];","$out.push(",");","$out.join('')"],y=h?"$out+=text;return $out;":"$out.push(text);",b="function(){var text=''.concat.apply('',arguments);"+y+"}",w="function(filename,data){data=data||$data;var text=$utils.$include(filename,data,$filename);"+y+"}",x="'use strict';var $utils=this,$helpers=$utils.$helpers,"+(o?"$line=0,":""),j=m[0],T="return new String("+m[3]+");";
v(r.split(c),function(e){e=e.split(l);var n=e[0],r=e[1];1===e.length?j+=a(n):(j+=i(n),r&&(j+=a(r)))});var k=x+j+T;o&&(k="try{"+k+"}catch(e){throw {filename:$filename,name:'Render Error',message:e.message,line:$line,source:"+n(r)+".split(/\\n/)[$line-1].replace(/^\\s+/,'')};}");
try{var E=new Function("$data","$filename",k);return E.prototype=g,E}catch(S){throw S.temp="function anonymous($data,$filename) {"+k+"}",S
}}var a=function(e,n){return"string"==typeof n?m(n,{filename:e}):c(e,n)};a.version="3.0.0",a.config=function(e,n){i[e]=n};
var i=a.defaults={openTag:"<%",closeTag:"%>",escape:!0,cache:!0,compress:!1,parser:null},o=a.cache={};a.render=function(e,n){return m(e,n)
};var c=a.renderFile=function(e,n){var r=a.get(e)||h({filename:e,name:"Render Error",message:"Template not found"});return n?r(n):r
};a.get=function(e){var n;if(o[e])n=o[e];else if("object"==typeof document){var r=document.getElementById(e);if(r){var t=(r.value||r.innerHTML).replace(/^\s*|\s*$/g,"");
n=m(t,{filename:e})}}return n};var l=function(e,n){return"string"!=typeof e&&(n=typeof e,"number"===n?e+="":e="function"===n?l(e.call(e)):""),e
},s={"<":"&#60;",">":"&#62;",'"':"&#34;","'":"&#39;","&":"&#38;"},u=function(e){return s[e]},f=function(e){return l(e).replace(/&(?![\w#]+;)|[<>"']/g,u)
},p=Array.isArray||function(e){return"[object Array]"==={}.toString.call(e)},$=function(e,n){var r,t;if(p(e))for(r=0,t=e.length;t>r;r++)n.call(e,e[r],r,e);
else for(r in e)n.call(e,e[r],r)},g=a.utils={$helpers:{},$include:c,$string:l,$escape:f,$each:$};a.helper=function(e,n){d[e]=n
};var d=a.helpers=g.$helpers;a.onerror=function(e){var n="Template Error\n\n";for(var r in e)n+="<"+r+">\n"+e[r]+"\n\n";"object"==typeof console&&console.error(n)
};var h=function(e){return a.onerror(e),function(){return"{Template Error}"}},m=a.compile=function(e,n){function r(r){try{return new l(r,c)+""
}catch(t){return n.debug?h(t)():(n.debug=!0,m(e,n)(r))}}n=n||{};for(var a in i)void 0===n[a]&&(n[a]=i[a]);var c=n.filename;
try{var l=t(e,n)}catch(s){return s.filename=c||"anonymous",s.name="Syntax Error",h(s)}return r.prototype=l.prototype,r.toString=function(){return l.toString()
},c&&n.cache&&(o[c]=r),r},v=g.$each,y="break,case,catch,continue,debugger,default,delete,do,else,false,finally,for,function,if,in,instanceof,new,null,return,switch,this,throw,true,try,typeof,var,void,while,with,abstract,boolean,byte,char,class,const,double,enum,export,extends,final,float,goto,implements,import,int,interface,long,native,package,private,protected,public,short,static,super,synchronized,throws,transient,volatile,arguments,let,yield,undefined",b=/\/\*[\w\W]*?\*\/|\/\/[^\n]*\n|\/\/[^\n]*$|"(?:[^"\\]|\\[\w\W])*"|'(?:[^'\\]|\\[\w\W])*'|\s*\.\s*[$\w\.]+/g,w=/[^\w$]+/g,x=new RegExp(["\\b"+y.replace(/,/g,"\\b|\\b")+"\\b"].join("|"),"g"),j=/^\d[^,]*|,\d[^,]*/g,T=/^,+|,+$/g,k=/^$|,+/;
i.openTag="{{",i.closeTag="}}";var E=function(e,n){var r=n.split(":"),t=r.shift(),a=r.join(":")||"";return a&&(a=", "+a),"$helpers."+t+"("+e+a+")"
};i.parser=function(e){e=e.replace(/^\s/,"");var n=e.split(" "),r=n.shift(),t=n.join(" ");switch(r){case"if":e="if("+t+"){";
break;case"else":n="if"===n.shift()?" if("+n.join(" ")+")":"",e="}else"+n+"{";break;case"/if":e="}";break;case"each":var i=n[0]||"$data",o=n[1]||"as",c=n[2]||"$value",l=n[3]||"$index",s=c+","+l;
"as"!==o&&(i="[]"),e="$each("+i+",function("+s+"){";break;case"/each":e="});";break;case"echo":e="print("+t+");";break;case"print":case"include":e=r+"("+n.join(",")+");";
break;default:if(/^\s*\|\s*[\w\$]/.test(t)){var u=!0;0===e.indexOf("#")&&(e=e.substr(1),u=!1);for(var f=0,p=e.split("|"),$=p.length,g=p[f++];$>f;f++)g=E(g,p[f]);
e=(u?"=":"=#")+g}else e=a.helpers[r]?"=#"+r+"("+n.join(",")+");":"="+e}return e},r.exports=a}()});
;define("translation:widget/common/third_party/webuploader/webuploader.fis",function(){var e=$;return function(t,n){var r={},i=function(e,t){var n,r,i;
if("string"==typeof e)return s(e);for(n=[],r=e.length,i=0;r>i;i++)n.push(s(e[i]));return t.apply(null,n)},o=function(e,t,n){2===arguments.length&&(n=t,t=null),i(t||[],function(){a(e,n,arguments)
})},a=function(e,t,n){var o,a={exports:t};"function"==typeof t&&(n.length||(n=[i,a.exports,a]),o=t.apply(null,n),void 0!==o&&(a.exports=o)),r[e]=a.exports
},s=function(e){var n=r[e]||t[e];if(!n)throw new Error("`"+e+"` is undefined");return n},u=function(e){var t,n,i,o,a,s;s=function(e){return e&&e.charAt(0).toUpperCase()+e.substr(1)
};for(t in r)if(n=e,r.hasOwnProperty(t)){for(i=t.split("/"),a=s(i.pop());o=s(i.shift());)n[o]=n[o]||{},n=n[o];n[a]=r[t]}return e
},c=function(e){return t.__dollar=e,u(n(t,o,i))};return c(e)}(window,function(e,t,n){t("dollar-third",[],function(){var t=e.__dollar||e.jQuery||e.Zepto;
if(!t)throw new Error("jQuery or Zepto not found!");return t}),t("dollar",["dollar-third"],function(e){return e}),t("promise-third",["dollar"],function(e){return{Deferred:e.Deferred,when:e.when,isPromise:function(e){return e&&"function"==typeof e.then
}}}),t("promise",["promise-third"],function(e){return e}),t("base",["dollar","promise"],function(t,n){function r(e){return function(){return s.apply(e,arguments)
}}function i(e,t){return function(){return e.apply(t,arguments)}}function o(e){var t;return Object.create?Object.create(e):(t=function(){},t.prototype=e,new t)
}var a=function(){},s=Function.call;return{version:"0.1.5",$:t,Deferred:n.Deferred,isPromise:n.isPromise,when:n.when,browser:function(e){var t={},n=e.match(/WebKit\/([\d.]+)/),r=e.match(/Chrome\/([\d.]+)/)||e.match(/CriOS\/([\d.]+)/),i=e.match(/MSIE\s([\d\.]+)/)||e.match(/(?:trident)(?:.*rv:([\w.]+))?/i),o=e.match(/Firefox\/([\d.]+)/),a=e.match(/Safari\/([\d.]+)/),s=e.match(/OPR\/([\d.]+)/);
return n&&(t.webkit=parseFloat(n[1])),r&&(t.chrome=parseFloat(r[1])),i&&(t.ie=parseFloat(i[1])),o&&(t.firefox=parseFloat(o[1])),a&&(t.safari=parseFloat(a[1])),s&&(t.opera=parseFloat(s[1])),t
}(navigator.userAgent),os:function(e){var t={},n=e.match(/(?:Android);?[\s\/]+([\d.]+)?/),r=e.match(/(?:iPad|iPod|iPhone).*OS\s([\d_]+)/);
return n&&(t.android=parseFloat(n[1])),r&&(t.ios=parseFloat(r[1].replace(/_/g,"."))),t}(navigator.userAgent),inherits:function(e,n,r){var i;
return"function"==typeof n?(i=n,n=null):i=n&&n.hasOwnProperty("constructor")?n.constructor:function(){return e.apply(this,arguments)
},t.extend(!0,i,e,r||{}),i.__super__=e.prototype,i.prototype=o(e.prototype),n&&t.extend(!0,i.prototype,n),i},noop:a,bindFn:i,log:function(){return e.console?i(console.log,console):a
}(),nextTick:function(){return function(e){setTimeout(e,1)}}(),slice:r([].slice),guid:function(){var e=0;return function(t){for(var n=(+new Date).toString(32),r=0;5>r;r++)n+=Math.floor(65535*Math.random()).toString(32);
return(t||"wu_")+n+(e++).toString(32)}}(),formatSize:function(e,t,n){var r;for(n=n||["B","K","M","G","TB"];(r=n.shift())&&e>1024;)e/=1024;
return("B"===r?e:e.toFixed(t||2))+r}}}),t("mediator",["base"],function(e){function t(e,t,n,r){return o.grep(e,function(e){return!(!e||t&&e.e!==t||n&&e.cb!==n&&e.cb._cb!==n||r&&e.ctx!==r)
})}function n(e,t,n){o.each((e||"").split(s),function(e,r){n(r,t)})}function r(e,t){for(var n,r=!1,i=-1,o=e.length;++i<o;)if(n=e[i],n.cb.apply(n.ctx2,t)===!1){r=!0;
break}return!r}var i,o=e.$,a=[].slice,s=/\s+/;return i={on:function(e,t,r){var i,o=this;return t?(i=this._events||(this._events=[]),n(e,t,function(e,t){var n={e:e};
n.cb=t,n.ctx=r,n.ctx2=r||o,n.id=i.length,i.push(n)}),this):this},once:function(e,t,r){var i=this;return t?(n(e,t,function(e,t){var n=function(){return i.off(e,n),t.apply(r||i,arguments)
};n._cb=t,i.on(e,n,r)}),i):i},off:function(e,r,i){var a=this._events;return a?e||r||i?(n(e,r,function(e,n){o.each(t(a,e,n,i),function(){delete a[this.id]
})}),this):(this._events=[],this):this},trigger:function(e){var n,i,o;return this._events&&e?(n=a.call(arguments,1),i=t(this._events,e),o=t(this._events,"all"),r(i,n)&&r(o,arguments)):this
}},o.extend({installTo:function(e){return o.extend(e,i)}},i)}),t("uploader",["base","mediator"],function(e,t){function n(e){this.options=r.extend(!0,{},n.options,e),this._init(this.options)
}var r=e.$;return n.options={},t.installTo(n.prototype),r.each({upload:"start-upload",stop:"stop-upload",getFile:"get-file",getFiles:"get-files",addFile:"add-file",addFiles:"add-file",sort:"sort-files",removeFile:"remove-file",cancelFile:"cancel-file",skipFile:"skip-file",retry:"retry",isInProgress:"is-in-progress",makeThumb:"make-thumb",md5File:"md5-file",getDimension:"get-dimension",addButton:"add-btn",predictRuntimeType:"predict-runtime-type",refresh:"refresh",disable:"disable",enable:"enable",reset:"reset"},function(e,t){n.prototype[e]=function(){return this.request(t,arguments)
}}),r.extend(n.prototype,{state:"pending",_init:function(e){var t=this;t.request("init",e,function(){t.state="ready",t.trigger("ready")
})},option:function(e,t){var n=this.options;return arguments.length>1?void(r.isPlainObject(t)&&r.isPlainObject(n[e])?r.extend(n[e],t):n[e]=t):e?n[e]:n
},getStats:function(){var e=this.request("get-stats");return e?{successNum:e.numOfSuccess,progressNum:e.numOfProgress,cancelNum:e.numOfCancel,invalidNum:e.numOfInvalid,uploadFailNum:e.numOfUploadFailed,queueNum:e.numOfQueue,interruptNum:e.numofInterrupt}:{}
},trigger:function(e){var n=[].slice.call(arguments,1),i=this.options,o="on"+e.substring(0,1).toUpperCase()+e.substring(1);
return t.trigger.apply(this,arguments)===!1||r.isFunction(i[o])&&i[o].apply(this,n)===!1||r.isFunction(this[o])&&this[o].apply(this,n)===!1||t.trigger.apply(t,[this,e].concat(n))===!1?!1:!0
},destroy:function(){this.request("destroy",arguments),this.off()},request:e.noop}),e.create=n.create=function(e){return new n(e)
},e.Uploader=n,n}),t("runtime/runtime",["base","mediator"],function(e,t){function n(t){this.options=r.extend({container:document.body},t),this.uid=e.guid("rt_")
}var r=e.$,i={},o=function(e){for(var t in e)if(e.hasOwnProperty(t))return t;return null};return r.extend(n.prototype,{getContainer:function(){var e,t,n=this.options;
return this._container?this._container:(e=r(n.container||document.body),t=r(document.createElement("div")),t.attr("id","rt_"+this.uid),t.css({position:"absolute",top:"0px",left:"0px",width:"1px",height:"1px",overflow:"hidden"}),e.append(t),e.addClass("webuploader-container"),this._container=t,this._parent=e,t)
},init:e.noop,exec:e.noop,destroy:function(){this._container&&this._container.remove(),this._parent&&this._parent.removeClass("webuploader-container"),this.off()
}}),n.orders="html5,flash",n.addRuntime=function(e,t){i[e]=t},n.hasRuntime=function(e){return!!(e?i[e]:o(i))},n.create=function(e,t){var a,s;
if(t=t||n.orders,r.each(t.split(/\s*,\s*/g),function(){return i[this]?(a=this,!1):void 0}),a=a||o(i),!a)throw new Error("Runtime Error");
return s=new i[a](e)},t.installTo(n.prototype),n}),t("runtime/client",["base","mediator","runtime/runtime"],function(e,t,n){function r(t,r){var o,a=e.Deferred();
this.uid=e.guid("client_"),this.runtimeReady=function(e){return a.done(e)},this.connectRuntime=function(t,s){if(o)throw new Error("already connected!");
return a.done(s),"string"==typeof t&&i.get(t)&&(o=i.get(t)),o=o||i.get(null,r),o?(e.$.extend(o.options,t),o.__promise.then(a.resolve),o.__client++):(o=n.create(t,t.runtimeOrder),o.__promise=a.promise(),o.once("ready",a.resolve),o.init(),i.add(o),o.__client=1),r&&(o.__standalone=r),o
},this.getRuntime=function(){return o},this.disconnectRuntime=function(){o&&(o.__client--,o.__client<=0&&(i.remove(o),delete o.__promise,o.destroy()),o=null)
},this.exec=function(){if(o){var n=e.slice(arguments);return t&&n.unshift(t),o.exec.apply(this,n)}},this.getRuid=function(){return o&&o.uid
},this.destroy=function(e){return function(){e&&e.apply(this,arguments),this.trigger("destroy"),this.off(),this.exec("destroy"),this.disconnectRuntime()
}}(this.destroy)}var i;return i=function(){var e={};return{add:function(t){e[t.uid]=t},get:function(t,n){var r;if(t)return e[t];
for(r in e)if(!n||!e[r].__standalone)return e[r];return null},remove:function(t){delete e[t.uid]}}}(),t.installTo(r.prototype),r
}),t("lib/dnd",["base","mediator","runtime/client"],function(e,t,n){function r(e){e=this.options=i.extend({},r.options,e),e.container=i(e.container),e.container.length&&n.call(this,"DragAndDrop")
}var i=e.$;return r.options={accept:null,disableGlobalDnd:!1},e.inherits(n,{constructor:r,init:function(){var e=this;e.connectRuntime(e.options,function(){e.exec("init"),e.trigger("ready")
})}}),t.installTo(r.prototype),r}),t("widgets/widget",["base","uploader"],function(e,t){function n(e){if(!e)return!1;var t=e.length,n=i.type(e);
return 1===e.nodeType&&t?!0:"array"===n||"function"!==n&&"string"!==n&&(0===t||"number"==typeof t&&t>0&&t-1 in e)}function r(e){this.owner=e,this.options=e.options
}var i=e.$,o=t.prototype._init,a=t.prototype.destroy,s={},u=[];return i.extend(r.prototype,{init:e.noop,invoke:function(e,t){var n=this.responseMap;
return n&&e in n&&n[e]in this&&i.isFunction(this[n[e]])?this[n[e]].apply(this,t):s},request:function(){return this.owner.request.apply(this.owner,arguments)
}}),i.extend(t.prototype,{_init:function(){var e=this,t=e._widgets=[],n=e.options.disableWidgets||"";return i.each(u,function(r,i){(!n||!~n.indexOf(i._name))&&t.push(new i(e))
}),o.apply(e,arguments)},request:function(t,r,i){var o,a,u,c,l=0,f=this._widgets,h=f&&f.length,d=[],p=[];for(r=n(r)?r:[r];h>l;l++)o=f[l],a=o.invoke(t,r),a!==s&&(e.isPromise(a)?p.push(a):d.push(a));
return i||p.length?(u=e.when.apply(e,p),c=u.pipe?"pipe":"then",u[c](function(){var t=e.Deferred(),n=arguments;return 1===n.length&&(n=n[0]),setTimeout(function(){t.resolve(n)
},1),t.promise()})[i?c:"done"](i||e.noop)):d[0]},destroy:function(){a.apply(this,arguments),this._widgets=null}}),t.register=r.register=function(t,n){var o,a={init:"init",destroy:"destroy",name:"anonymous"};
return 1===arguments.length?(n=t,i.each(n,function(e){return"_"===e[0]||"name"===e?void("name"===e&&(a.name=n.name)):void(a[e.replace(/[A-Z]/g,"-$&").toLowerCase()]=e)
})):a=i.extend(a,t),n.responseMap=a,o=e.inherits(r,n),o._name=a.name,u.push(o),o},t.unRegister=r.unRegister=function(e){if(e&&"anonymous"!==e)for(var t=u.length;t--;)u[t]._name===e&&u.splice(t,1)
},r}),t("widgets/filednd",["base","uploader","lib/dnd","widgets/widget"],function(e,t,n){var r=e.$;return t.options.dnd="",t.register({name:"dnd",init:function(t){if(t.dnd&&"html5"===this.request("predict-runtime-type")){var i,o=this,a=e.Deferred(),s=r.extend({},{disableGlobalDnd:t.disableGlobalDnd,container:t.dnd,accept:t.accept});
return this.dnd=i=new n(s),i.once("ready",a.resolve),i.on("drop",function(e){o.request("add-file",[e])}),i.on("accept",function(e){return o.owner.trigger("dndAccept",e)
}),i.init(),a.promise()}},destroy:function(){this.dnd&&this.dnd.destroy()}})}),t("lib/filepaste",["base","mediator","runtime/client"],function(e,t,n){function r(e){e=this.options=i.extend({},e),e.container=i(e.container||document.body),n.call(this,"FilePaste")
}var i=e.$;return e.inherits(n,{constructor:r,init:function(){var e=this;e.connectRuntime(e.options,function(){e.exec("init"),e.trigger("ready")
})}}),t.installTo(r.prototype),r}),t("widgets/filepaste",["base","uploader","lib/filepaste","widgets/widget"],function(e,t,n){var r=e.$;
return t.register({name:"paste",init:function(t){if(t.paste&&"html5"===this.request("predict-runtime-type")){var i,o=this,a=e.Deferred(),s=r.extend({},{container:t.paste,accept:t.accept});
return this.paste=i=new n(s),i.once("ready",a.resolve),i.on("paste",function(e){o.owner.request("add-file",[e])}),i.init(),a.promise()
}},destroy:function(){this.paste&&this.paste.destroy()}})}),t("lib/blob",["base","runtime/client"],function(e,t){function n(e,n){var r=this;
r.source=n,r.ruid=e,this.size=n.size||0,this.type=!n.type&&this.ext&&~"jpg,jpeg,png,gif,bmp".indexOf(this.ext)?"image/"+("jpg"===this.ext?"jpeg":this.ext):n.type||"application/octet-stream",t.call(r,"Blob"),this.uid=n.uid||this.uid,e&&r.connectRuntime(e)
}return e.inherits(t,{constructor:n,slice:function(e,t){return this.exec("slice",e,t)},getSource:function(){return this.source
}}),n}),t("lib/file",["base","lib/blob"],function(e,t){function n(e,n){var o;this.name=n.name||"untitled"+r++,o=i.exec(n.name)?RegExp.$1.toLowerCase():"",!o&&n.type&&(o=/\/(jpg|jpeg|png|gif|bmp)$/i.exec(n.type)?RegExp.$1.toLowerCase():"",this.name+="."+o),this.ext=o,this.lastModifiedDate=n.lastModifiedDate||(new Date).toLocaleString(),t.apply(this,arguments)
}var r=1,i=/\.([^.]+)$/;return e.inherits(t,n)}),t("lib/filepicker",["base","runtime/client","lib/file"],function(t,n,r){function i(e){if(e=this.options=o.extend({},i.options,e),e.container=o(e.id),!e.container.length)throw new Error("按钮指定错误");
e.innerHTML=e.innerHTML||e.label||e.container.html()||"",e.button=o(e.button||document.createElement("div")),e.button.html(e.innerHTML),e.container.html(e.button),n.call(this,"FilePicker",!0)
}var o=t.$;return i.options={button:null,container:null,label:null,innerHTML:null,multiple:!0,accept:null,name:"file"},t.inherits(n,{constructor:i,init:function(){var n=this,i=n.options,a=i.button;
a.addClass("webuploader-pick"),n.on("all",function(e){var t;switch(e){case"mouseenter":a.addClass("webuploader-pick-hover");
break;case"mouseleave":a.removeClass("webuploader-pick-hover");break;case"change":t=n.exec("getFiles"),n.trigger("select",o.map(t,function(e){return e=new r(n.getRuid(),e),e._refer=i.container,e
}),i.container)}}),n.connectRuntime(i,function(){n.refresh(),n.exec("init",i),n.trigger("ready")}),this._resizeHandler=t.bindFn(this.refresh,this),o(e).on("resize",this._resizeHandler)
},refresh:function(){var e=this.getRuntime().getContainer(),t=this.options.button,n=t.outerWidth?t.outerWidth():t.width(),r=t.outerHeight?t.outerHeight():t.height(),i=t.offset();
n&&r&&e.css({bottom:"auto",right:"auto",width:n+"px",height:r+"px"}).offset(i)},enable:function(){var e=this.options.button;
e.removeClass("webuploader-pick-disable"),this.refresh()},disable:function(){var e=this.options.button;this.getRuntime().getContainer().css({top:"-99999px"}),e.addClass("webuploader-pick-disable")
},destroy:function(){var t=this.options.button;o(e).off("resize",this._resizeHandler),t.removeClass("webuploader-pick-disable webuploader-pick-hover webuploader-pick")
}}),i}),t("widgets/filepicker",["base","uploader","lib/filepicker","widgets/widget"],function(e,t,n){var r=e.$;return r.extend(t.options,{pick:null,accept:null}),t.register({name:"picker",init:function(e){return this.pickers=[],e.pick&&this.addBtn(e.pick)
},refresh:function(){r.each(this.pickers,function(){this.refresh()})},addBtn:function(t){var i=this,o=i.options,a=o.accept,s=[];
if(t)return r.isPlainObject(t)||(t={id:t}),r(t.id).each(function(){var u,c,l;l=e.Deferred(),u=r.extend({},t,{accept:r.isPlainObject(a)?[a]:a,swf:o.swf,runtimeOrder:o.runtimeOrder,id:this}),c=new n(u),c.once("ready",l.resolve),c.on("select",function(e){i.owner.request("add-file",[e])
}),c.init(),i.pickers.push(c),s.push(l.promise())}),e.when.apply(e,s)},disable:function(){r.each(this.pickers,function(){this.disable()
})},enable:function(){r.each(this.pickers,function(){this.enable()})},destroy:function(){r.each(this.pickers,function(){this.destroy()
}),this.pickers=null}})}),t("lib/image",["base","runtime/client","lib/blob"],function(e,t,n){function r(e){this.options=i.extend({},r.options,e),t.call(this,"Image"),this.on("load",function(){this._info=this.exec("info"),this._meta=this.exec("meta")
})}var i=e.$;return r.options={quality:90,crop:!1,preserveHeaders:!1,allowMagnify:!1},e.inherits(t,{constructor:r,info:function(e){return e?(this._info=e,this):this._info
},meta:function(e){return e?(this._meta=e,this):this._meta},loadFromBlob:function(e){var t=this,n=e.getRuid();this.connectRuntime(n,function(){t.exec("init",t.options),t.exec("loadFromBlob",e)
})},resize:function(){var t=e.slice(arguments);return this.exec.apply(this,["resize"].concat(t))},crop:function(){var t=e.slice(arguments);
return this.exec.apply(this,["crop"].concat(t))},getAsDataUrl:function(e){return this.exec("getAsDataUrl",e)},getAsBlob:function(e){var t=this.exec("getAsBlob",e);
return new n(this.getRuid(),t)}}),r}),t("widgets/image",["base","uploader","lib/image","widgets/widget"],function(e,t,n){var r,i=e.$;
return r=function(e){var t=0,n=[],r=function(){for(var r;n.length&&e>t;)r=n.shift(),t+=r[0],r[1]()};return function(e,i,o){n.push([i,o]),e.once("destroy",function(){t-=i,setTimeout(r,1)
}),setTimeout(r,1)}}(5242880),i.extend(t.options,{thumb:{width:110,height:110,quality:70,allowMagnify:!0,crop:!0,preserveHeaders:!1,type:"image/jpeg"},compress:{width:1600,height:1600,quality:90,allowMagnify:!1,crop:!1,preserveHeaders:!0}}),t.register({name:"image",makeThumb:function(e,t,o,a){var s,u;
return e=this.request("get-file",e),e.type.match(/^image/)?(s=i.extend({},this.options.thumb),i.isPlainObject(o)&&(s=i.extend(s,o),o=null),o=o||s.width,a=a||s.height,u=new n(s),u.once("load",function(){e._info=e._info||u.info(),e._meta=e._meta||u.meta(),1>=o&&o>0&&(o=e._info.width*o),1>=a&&a>0&&(a=e._info.height*a),u.resize(o,a)
}),u.once("complete",function(){t(!1,u.getAsDataUrl(s.type)),u.destroy()}),u.once("error",function(e){t(e||!0),u.destroy()
}),void r(u,e.source.size,function(){e._info&&u.info(e._info),e._meta&&u.meta(e._meta),u.loadFromBlob(e.source)})):void t(!0)
},beforeSendFile:function(t){var r,o,a=this.options.compress||this.options.resize,s=a&&a.compressSize||0,u=a&&a.noCompressIfLarger||!1;
return t=this.request("get-file",t),!a||!~"image/jpeg,image/jpg".indexOf(t.type)||t.size<s||t._compressed?void 0:(a=i.extend({},a),o=e.Deferred(),r=new n(a),o.always(function(){r.destroy(),r=null
}),r.once("error",o.reject),r.once("load",function(){var e=a.width,n=a.height;t._info=t._info||r.info(),t._meta=t._meta||r.meta(),1>=e&&e>0&&(e=t._info.width*e),1>=n&&n>0&&(n=t._info.height*n),r.resize(e,n)
}),r.once("complete",function(){var e,n;try{e=r.getAsBlob(a.type),n=t.size,(!u||e.size<n)&&(t.source=e,t.size=e.size,t.trigger("resize",e.size,n)),t._compressed=!0,o.resolve()
}catch(i){o.resolve()}}),t._info&&r.info(t._info),t._meta&&r.meta(t._meta),r.loadFromBlob(t.source),o.promise())}})}),t("file",["base","mediator"],function(e,t){function n(){return o+a++
}function r(e){this.name=e.name||"Untitled",this.size=e.size||0,this.type=e.type||"application/octet-stream",this.lastModifiedDate=e.lastModifiedDate||1*new Date,this.id=n(),this.ext=s.exec(this.name)?RegExp.$1:"",this.statusText="",u[this.id]=r.Status.INITED,this.source=e,this.loaded=0,this.on("error",function(e){this.setStatus(r.Status.ERROR,e)
})}var i=e.$,o="WU_FILE_",a=0,s=/\.([^.]+)$/,u={};return i.extend(r.prototype,{setStatus:function(e,t){var n=u[this.id];"undefined"!=typeof t&&(this.statusText=t),e!==n&&(u[this.id]=e,this.trigger("statuschange",e,n))
},getStatus:function(){return u[this.id]},getSource:function(){return this.source},destroy:function(){this.off(),delete u[this.id]
}}),t.installTo(r.prototype),r.Status={INITED:"inited",QUEUED:"queued",PROGRESS:"progress",ERROR:"error",COMPLETE:"complete",CANCELLED:"cancelled",INTERRUPT:"interrupt",INVALID:"invalid"},r
}),t("queue",["base","mediator","file"],function(e,t,n){function r(){this.stats={numOfQueue:0,numOfSuccess:0,numOfCancel:0,numOfProgress:0,numOfUploadFailed:0,numOfInvalid:0,numofDeleted:0,numofInterrupt:0},this._queue=[],this._map={}
}var i=e.$,o=n.Status;return i.extend(r.prototype,{append:function(e){return this._queue.push(e),this._fileAdded(e),this},prepend:function(e){return this._queue.unshift(e),this._fileAdded(e),this
},getFile:function(e){return"string"!=typeof e?e:this._map[e]},fetch:function(e){var t,n,r=this._queue.length;for(e=e||o.QUEUED,t=0;r>t;t++)if(n=this._queue[t],e===n.getStatus())return n;
return null},sort:function(e){"function"==typeof e&&this._queue.sort(e)},getFiles:function(){for(var e,t=[].slice.call(arguments,0),n=[],r=0,o=this._queue.length;o>r;r++)e=this._queue[r],(!t.length||~i.inArray(e.getStatus(),t))&&n.push(e);
return n},removeFile:function(e){var t=this._map[e.id];t&&(delete this._map[e.id],e.destroy(),this.stats.numofDeleted++)},_fileAdded:function(e){var t=this,n=this._map[e.id];
n||(this._map[e.id]=e,e.on("statuschange",function(e,n){t._onFileStatusChange(e,n)}))},_onFileStatusChange:function(e,t){var n=this.stats;
switch(t){case o.PROGRESS:n.numOfProgress--;break;case o.QUEUED:n.numOfQueue--;break;case o.ERROR:n.numOfUploadFailed--;break;
case o.INVALID:n.numOfInvalid--;break;case o.INTERRUPT:n.numofInterrupt--}switch(e){case o.QUEUED:n.numOfQueue++;break;case o.PROGRESS:n.numOfProgress++;
break;case o.ERROR:n.numOfUploadFailed++;break;case o.COMPLETE:n.numOfSuccess++;break;case o.CANCELLED:n.numOfCancel++;break;
case o.INVALID:n.numOfInvalid++;break;case o.INTERRUPT:n.numofInterrupt++}}}),t.installTo(r.prototype),r}),t("widgets/queue",["base","uploader","queue","file","lib/file","runtime/client","widgets/widget"],function(e,t,n,r,i,o){var a=e.$,s=/\.\w+$/,u=r.Status;
return t.register({name:"queue",init:function(t){var r,i,s,u,c,l,f,h=this;if(a.isPlainObject(t.accept)&&(t.accept=[t.accept]),t.accept){for(c=[],s=0,i=t.accept.length;i>s;s++)u=t.accept[s].extensions,u&&c.push(u);
c.length&&(l="\\."+c.join(",").replace(/,/g,"$|\\.").replace(/\*/g,".*")+"$"),h.accept=new RegExp(l,"i")}return h.queue=new n,h.stats=h.queue.stats,"html5"===this.request("predict-runtime-type")?(r=e.Deferred(),this.placeholder=f=new o("Placeholder"),f.connectRuntime({runtimeOrder:"html5"},function(){h._ruid=f.getRuid(),r.resolve()
}),r.promise()):void 0},_wrapFile:function(e){if(!(e instanceof r)){if(!(e instanceof i)){if(!this._ruid)throw new Error("Can't add external files.");
e=new i(this._ruid,e)}e=new r(e)}return e},acceptFile:function(e){var t=!e||!e.size||this.accept&&s.exec(e.name)&&!this.accept.test(e.name);
return!t},_addFile:function(e){var t=this;return e=t._wrapFile(e),t.owner.trigger("beforeFileQueued",e)?t.acceptFile(e)?(t.queue.append(e),t.owner.trigger("fileQueued",e),e):void t.owner.trigger("error","Q_TYPE_DENIED",e):void 0
},getFile:function(e){return this.queue.getFile(e)},addFile:function(e){var t=this;e.length||(e=[e]),e=a.map(e,function(e){return t._addFile(e)
}),t.owner.trigger("filesQueued",e),t.options.auto&&setTimeout(function(){t.request("start-upload")},20)},getStats:function(){return this.stats
},removeFile:function(e,t){var n=this;e=e.id?e:n.queue.getFile(e),this.request("cancel-file",e),t&&this.queue.removeFile(e)
},getFiles:function(){return this.queue.getFiles.apply(this.queue,arguments)},fetchFile:function(){return this.queue.fetch.apply(this.queue,arguments)
},retry:function(e,t){var n,r,i,o=this;if(e)return e=e.id?e:o.queue.getFile(e),e.setStatus(u.QUEUED),void(t||o.request("start-upload"));
for(n=o.queue.getFiles(u.ERROR),r=0,i=n.length;i>r;r++)e=n[r],e.setStatus(u.QUEUED);o.request("start-upload")},sortFiles:function(){return this.queue.sort.apply(this.queue,arguments)
},reset:function(){this.owner.trigger("reset"),this.queue=new n,this.stats=this.queue.stats},destroy:function(){this.reset(),this.placeholder&&this.placeholder.destroy()
}})}),t("widgets/runtime",["uploader","runtime/runtime","widgets/widget"],function(e,t){return e.support=function(){return t.hasRuntime.apply(t,arguments)
},e.register({name:"runtime",init:function(){if(!this.predictRuntimeType())throw Error("Runtime Error")},predictRuntimeType:function(){var e,n,r=this.options.runtimeOrder||t.orders,i=this.type;
if(!i)for(r=r.split(/\s*,\s*/g),e=0,n=r.length;n>e;e++)if(t.hasRuntime(r[e])){this.type=i=r[e];break}return i}})}),t("lib/transport",["base","runtime/client","mediator"],function(e,t,n){function r(e){var n=this;
e=n.options=i.extend(!0,{},r.options,e||{}),t.call(this,"Transport"),this._blob=null,this._formData=e.formData||{},this._headers=e.headers||{},this.on("progress",this._timeout),this.on("load error",function(){n.trigger("progress",1),clearTimeout(n._timer)
})}var i=e.$;return r.options={server:"",method:"POST",withCredentials:!1,fileVal:"file",timeout:12e4,formData:{},headers:{},sendAsBinary:!1},i.extend(r.prototype,{appendBlob:function(e,t,n){var r=this,i=r.options;
r.getRuid()&&r.disconnectRuntime(),r.connectRuntime(t.ruid,function(){r.exec("init")}),r._blob=t,i.fileVal=e||i.fileVal,i.filename=n||i.filename
},append:function(e,t){"object"==typeof e?i.extend(this._formData,e):this._formData[e]=t},setRequestHeader:function(e,t){"object"==typeof e?i.extend(this._headers,e):this._headers[e]=t
},send:function(e){this.exec("send",e),this._timeout()},abort:function(){return clearTimeout(this._timer),this.exec("abort")
},destroy:function(){this.trigger("destroy"),this.off(),this.exec("destroy"),this.disconnectRuntime()},getResponse:function(){return this.exec("getResponse")
},getResponseAsJson:function(){return this.exec("getResponseAsJson")},getStatus:function(){return this.exec("getStatus")},_timeout:function(){var e=this,t=e.options.timeout;
t&&(clearTimeout(e._timer),e._timer=setTimeout(function(){e.abort(),e.trigger("error","timeout")},t))}}),n.installTo(r.prototype),r
}),t("widgets/upload",["base","uploader","file","lib/transport","widgets/widget"],function(e,t,n,r){function i(e,t){var n,r,i=[],o=e.source,a=o.size,s=t?Math.ceil(a/t):1,u=0,c=0;
for(r={file:e,has:function(){return!!i.length},shift:function(){return i.shift()},unshift:function(e){i.unshift(e)}};s>c;)n=Math.min(t,a-u),i.push({file:e,start:u,end:t?u+n:a,total:a,chunks:s,chunk:c++,cuted:r}),u+=n;
return e.blocks=i.concat(),e.remaning=i.length,r}var o=e.$,a=e.isPromise,s=n.Status;o.extend(t.options,{prepareNextFile:!1,chunked:!1,chunkSize:5242880,chunkRetry:2,threads:3,formData:{}}),t.register({name:"upload",init:function(){var t=this.owner,n=this;
this.runing=!1,this.progress=!1,t.on("startUpload",function(){n.progress=!0}).on("uploadFinished",function(){n.progress=!1
}),this.pool=[],this.stack=[],this.pending=[],this.remaning=0,this.__tick=e.bindFn(this._tick,this),t.on("uploadComplete",function(e){e.blocks&&o.each(e.blocks,function(e,t){t.transport&&(t.transport.abort(),t.transport.destroy()),delete t.transport
}),delete e.blocks,delete e.remaning})},reset:function(){this.request("stop-upload",!0),this.runing=!1,this.pool=[],this.stack=[],this.pending=[],this.remaning=0,this._trigged=!1,this._promise=null
},startUpload:function(t){var n=this;if(o.each(n.request("get-files",s.INVALID),function(){n.request("remove-file",this)}),t)if(t=t.id?t:n.request("get-file",t),t.getStatus()===s.INTERRUPT)o.each(n.pool,function(e,n){n.file===t&&n.transport&&n.transport.send()
}),t.setStatus(s.QUEUED);else{if(t.getStatus()===s.PROGRESS)return;t.setStatus(s.QUEUED)}else o.each(n.request("get-files",[s.INITED]),function(){this.setStatus(s.QUEUED)
});if(!n.runing){n.runing=!0;var r=[];o.each(n.pool,function(e,t){var i=t.file;i.getStatus()===s.INTERRUPT&&(r.push(i),n._trigged=!1,t.transport&&t.transport.send())
});for(var t;t=r.shift();)t.setStatus(s.PROGRESS);t||o.each(n.request("get-files",s.INTERRUPT),function(){this.setStatus(s.PROGRESS)
}),n._trigged=!1,e.nextTick(n.__tick),n.owner.trigger("startUpload")}},stopUpload:function(t,n){var r=this;if(t===!0&&(n=t,t=null),r.runing!==!1){if(t){if(t=t.id?t:r.request("get-file",t),t.getStatus()!==s.PROGRESS&&t.getStatus()!==s.QUEUED)return;
return t.setStatus(s.INTERRUPT),o.each(r.pool,function(e,n){n.file===t&&(n.transport&&n.transport.abort(),r._putback(n),r._popBlock(n))
}),e.nextTick(r.__tick)}r.runing=!1,this._promise&&this._promise.file&&this._promise.file.setStatus(s.INTERRUPT),n&&o.each(r.pool,function(e,t){t.transport&&t.transport.abort(),t.file.setStatus(s.INTERRUPT)
}),r.owner.trigger("stopUpload")}},cancelFile:function(e){e=e.id?e:this.request("get-file",e),e.blocks&&o.each(e.blocks,function(e,t){var n=t.transport;
n&&(n.abort(),n.destroy(),delete t.transport)}),e.setStatus(s.CANCELLED),this.owner.trigger("fileDequeued",e)},isInProgress:function(){return!!this.progress
},_getStats:function(){return this.request("get-stats")},skipFile:function(e,t){e=e.id?e:this.request("get-file",e),e.setStatus(t||s.COMPLETE),e.skipped=!0,e.blocks&&o.each(e.blocks,function(e,t){var n=t.transport;
n&&(n.abort(),n.destroy(),delete t.transport)}),this.owner.trigger("uploadSkip",e)},_tick:function(){var t,n,r=this,i=r.options;
return r._promise?r._promise.always(r.__tick):void(r.pool.length<i.threads&&(n=r._nextBlock())?(r._trigged=!1,t=function(t){r._promise=null,t&&t.file&&r._startSend(t),e.nextTick(r.__tick)
},r._promise=a(n)?n.always(t):t(n)):r.remaning||r._getStats().numOfQueue||r._getStats().numofInterrupt||(r.runing=!1,r._trigged||e.nextTick(function(){r.owner.trigger("uploadFinished")
}),r._trigged=!0))},_putback:function(e){var t;e.cuted.unshift(e),t=this.stack.indexOf(e.cuted),~t||this.stack.unshift(e.cuted)
},_getStack:function(){for(var e,t=0;e=this.stack[t++];){if(e.has()&&e.file.getStatus()===s.PROGRESS)return e;(!e.has()||e.file.getStatus()!==s.PROGRESS&&e.file.getStatus()!==s.INTERRUPT)&&this.stack.splice(--t,1)
}return null},_nextBlock:function(){var e,t,n,r,o=this,s=o.options;return(e=this._getStack())?(s.prepareNextFile&&!o.pending.length&&o._prepareNextFile(),e.shift()):o.runing?(!o.pending.length&&o._getStats().numOfQueue&&o._prepareNextFile(),t=o.pending.shift(),n=function(t){return t?(e=i(t,s.chunked?s.chunkSize:0),o.stack.push(e),e.shift()):null
},a(t)?(r=t.file,t=t[t.pipe?"pipe":"then"](n),t.file=r,t):n(t)):void 0},_prepareNextFile:function(){var e,t=this,n=t.request("fetch-file"),r=t.pending;
n&&(e=t.request("before-send-file",n,function(){return n.getStatus()===s.PROGRESS||n.getStatus()===s.INTERRUPT?n:t._finishFile(n)
}),t.owner.trigger("uploadStart",n),n.setStatus(s.PROGRESS),e.file=n,e.done(function(){var t=o.inArray(e,r);~t&&r.splice(t,1,n)
}),e.fail(function(e){n.setStatus(s.ERROR,e),t.owner.trigger("uploadError",n,e),t.owner.trigger("uploadComplete",n)}),r.push(e))
},_popBlock:function(e){var t=o.inArray(e,this.pool);this.pool.splice(t,1),e.file.remaning--,this.remaning--},_startSend:function(t){var n,r=this,i=t.file;
return i.getStatus()!==s.PROGRESS?void(i.getStatus()===s.INTERRUPT&&r._putback(t)):(r.pool.push(t),r.remaning++,t.blob=1===t.chunks?i.source:i.source.slice(t.start,t.end),n=r.request("before-send",t,function(){i.getStatus()===s.PROGRESS?r._doSend(t):(r._popBlock(t),e.nextTick(r.__tick))
}),void n.fail(function(){1===i.remaning?r._finishFile(i).always(function(){t.percentage=1,r._popBlock(t),r.owner.trigger("uploadComplete",i),e.nextTick(r.__tick)
}):(t.percentage=1,r.updateFileProgress(i),r._popBlock(t),e.nextTick(r.__tick))}))},_doSend:function(t){var n,i,a=this,u=a.owner,c=a.options,l=t.file,f=new r(c),h=o.extend({},c.formData),d=o.extend({},c.headers);
t.transport=f,f.on("destroy",function(){delete t.transport,a._popBlock(t),e.nextTick(a.__tick)}),f.on("progress",function(e){t.percentage=e,a.updateFileProgress(l)
}),n=function(e){var n;return i=f.getResponseAsJson()||{},i._raw=f.getResponse(),n=function(t){e=t},u.trigger("uploadAccept",t,i,n)||(e=e||"server"),e
},f.on("error",function(e,r){t.retried=t.retried||0,t.chunks>1&&~"http,abort".indexOf(e)&&t.retried<c.chunkRetry?(t.retried++,f.send()):(r||"server"!==e||(e=n(e)),l.setStatus(s.ERROR,e),u.trigger("uploadError",l,e),u.trigger("uploadComplete",l))
}),f.on("load",function(){var e;return(e=n())?void f.trigger("error",e,!0):void(1===l.remaning?a._finishFile(l,i):f.destroy())
}),h=o.extend(h,{id:l.id,name:l.name,type:l.type,lastModifiedDate:l.lastModifiedDate,size:l.size}),t.chunks>1&&o.extend(h,{chunks:t.chunks,chunk:t.chunk}),u.trigger("uploadBeforeSend",t,h,d),f.appendBlob(c.fileVal,t.blob,l.name),f.append(h),f.setRequestHeader(d),f.send()
},_finishFile:function(e,t,n){var r=this.owner;return r.request("after-send-file",arguments,function(){e.setStatus(s.COMPLETE),r.trigger("uploadSuccess",e,t,n)
}).fail(function(t){e.getStatus()===s.PROGRESS&&e.setStatus(s.ERROR,t),r.trigger("uploadError",e,t)}).always(function(){r.trigger("uploadComplete",e)
})},updateFileProgress:function(e){var t=0,n=0;e.blocks&&(o.each(e.blocks,function(e,t){n+=(t.percentage||0)*(t.end-t.start)
}),t=n/e.size,this.owner.trigger("uploadProgress",e,t||0))}})}),t("widgets/validator",["base","uploader","file","widgets/widget"],function(e,t,n){var r,i=e.$,o={};
return r={addValidator:function(e,t){o[e]=t},removeValidator:function(e){delete o[e]}},t.register({name:"validator",init:function(){var t=this;
e.nextTick(function(){i.each(o,function(){this.call(t.owner)})})}}),r.addValidator("fileNumLimit",function(){var e=this,t=e.options,n=0,r=parseInt(t.fileNumLimit,10),i=!0;
r&&(e.on("beforeFileQueued",function(e){return n>=r&&i&&(i=!1,this.trigger("error","Q_EXCEED_NUM_LIMIT",r,e),setTimeout(function(){i=!0
},1)),n>=r?!1:!0}),e.on("fileQueued",function(){n++}),e.on("fileDequeued",function(){n--}),e.on("reset",function(){n=0}))
}),r.addValidator("fileSizeLimit",function(){var e=this,t=e.options,n=0,r=parseInt(t.fileSizeLimit,10),i=!0;r&&(e.on("beforeFileQueued",function(e){var t=n+e.size>r;
return t&&i&&(i=!1,this.trigger("error","Q_EXCEED_SIZE_LIMIT",r,e),setTimeout(function(){i=!0},1)),t?!1:!0}),e.on("fileQueued",function(e){n+=e.size
}),e.on("fileDequeued",function(e){n-=e.size}),e.on("reset",function(){n=0}))}),r.addValidator("fileSingleSizeLimit",function(){var e=this,t=e.options,r=t.fileSingleSizeLimit;
r&&e.on("beforeFileQueued",function(e){return e.size>r?(e.setStatus(n.Status.INVALID,"exceed_size"),this.trigger("error","F_EXCEED_SIZE",r,e),!1):void 0
})}),r.addValidator("duplicate",function(){function e(e){for(var t,n=0,r=0,i=e.length;i>r;r++)t=e.charCodeAt(r),n=t+(n<<6)+(n<<16)-n;
return n}var t=this,n=t.options,r={};n.duplicate||(t.on("beforeFileQueued",function(t){var n=t.__hash||(t.__hash=e(t.name));
return r[n]?(this.trigger("error","F_DUPLICATE",t),!1):void 0}),t.on("fileQueued",function(e){var t=e.__hash;t&&(r[t]=!0)
}),t.on("fileDequeued",function(e){var t=e.__hash;t&&delete r[t]}),t.on("reset",function(){r={}}))}),r}),t("lib/md5",["runtime/client","mediator"],function(e,t){function n(){e.call(this,"Md5")
}return t.installTo(n.prototype),n.prototype.loadFromBlob=function(e){var t=this;t.getRuid()&&t.disconnectRuntime(),t.connectRuntime(e.ruid,function(){t.exec("init"),t.exec("loadFromBlob",e)
})},n.prototype.getResult=function(){return this.exec("getResult")},n}),t("widgets/md5",["base","uploader","lib/md5","lib/blob","widgets/widget"],function(e,t,n,r){return t.register({name:"md5",md5File:function(t,i,o){var a=new n,s=e.Deferred(),u=t instanceof r?t:this.request("get-file",t).source;
return a.on("progress load",function(e){e=e||{},s.notify(e.total?e.loaded/e.total:1)}),a.on("complete",function(){s.resolve(a.getResult())
}),a.on("error",function(e){s.reject(e)}),arguments.length>1&&(i=i||0,o=o||0,0>i&&(i=u.size+i),0>o&&(o=u.size+o),o=Math.min(o,u.size),u=u.slice(i,o)),a.loadFromBlob(u),s.promise()
}})}),t("runtime/compbase",[],function(){function e(e,t){this.owner=e,this.options=e.options,this.getRuntime=function(){return t
},this.getRuid=function(){return t.uid},this.trigger=function(){return e.trigger.apply(e,arguments)}}return e}),t("runtime/html5/runtime",["base","runtime/runtime","runtime/compbase"],function(t,n,r){function i(){var e={},r=this,i=this.destroy;
n.apply(r,arguments),r.type=o,r.exec=function(n,i){var o,s=this,u=s.uid,c=t.slice(arguments,2);return a[n]&&(o=e[u]=e[u]||new a[n](s,r),o[i])?o[i].apply(o,c):void 0
},r.destroy=function(){return i&&i.apply(this,arguments)}}var o="html5",a={};return t.inherits(n,{constructor:i,init:function(){var e=this;
setTimeout(function(){e.trigger("ready")},1)}}),i.register=function(e,n){var i=a[e]=t.inherits(r,n);return i},e.Blob&&e.FileReader&&e.DataView&&n.addRuntime(o,i),i
}),t("runtime/html5/blob",["runtime/html5/runtime","lib/blob"],function(e,t){return e.register("Blob",{slice:function(e,n){var r=this.owner.source,i=r.slice||r.webkitSlice||r.mozSlice;
return r=i.call(r,e,n),new t(this.getRuid(),r)}})}),t("runtime/html5/dnd",["base","runtime/html5/runtime","lib/file"],function(e,t,n){var r=e.$,i="webuploader-dnd-";
return t.register("DragAndDrop",{init:function(){var t=this.elem=this.options.container;this.dragEnterHandler=e.bindFn(this._dragEnterHandler,this),this.dragOverHandler=e.bindFn(this._dragOverHandler,this),this.dragLeaveHandler=e.bindFn(this._dragLeaveHandler,this),this.dropHandler=e.bindFn(this._dropHandler,this),this.dndOver=!1,t.on("dragenter",this.dragEnterHandler),t.on("dragover",this.dragOverHandler),t.on("dragleave",this.dragLeaveHandler),t.on("drop",this.dropHandler),this.options.disableGlobalDnd&&(r(document).on("dragover",this.dragOverHandler),r(document).on("drop",this.dropHandler))
},_dragEnterHandler:function(e){var t,n=this,r=n._denied||!1;return e=e.originalEvent||e,n.dndOver||(n.dndOver=!0,t=e.dataTransfer.items,t&&t.length&&(n._denied=r=!n.trigger("accept",t)),n.elem.addClass(i+"over"),n.elem[r?"addClass":"removeClass"](i+"denied")),e.dataTransfer.dropEffect=r?"none":"copy",!1
},_dragOverHandler:function(e){var t=this.elem.parent().get(0);return t&&!r.contains(t,e.currentTarget)?!1:(clearTimeout(this._leaveTimer),this._dragEnterHandler.call(this,e),!1)
},_dragLeaveHandler:function(){var e,t=this;return e=function(){t.dndOver=!1,t.elem.removeClass(i+"over "+i+"denied")},clearTimeout(t._leaveTimer),t._leaveTimer=setTimeout(e,100),!1
},_dropHandler:function(e){var t,o,a=this,s=a.getRuid(),u=a.elem.parent().get(0);if(u&&!r.contains(u,e.currentTarget))return!1;
e=e.originalEvent||e,t=e.dataTransfer;try{o=t.getData("text/html")}catch(c){}return o?void 0:(a._getTansferFiles(t,function(e){a.trigger("drop",r.map(e,function(e){return new n(s,e)
}))}),a.dndOver=!1,a.elem.removeClass(i+"over"),!1)},_getTansferFiles:function(t,n){var r,i,o,a,s,u,c,l=[],f=[];for(r=t.items,i=t.files,c=!(!r||!r[0].webkitGetAsEntry),s=0,u=i.length;u>s;s++)o=i[s],a=r&&r[s],c&&a.webkitGetAsEntry().isDirectory?f.push(this._traverseDirectoryTree(a.webkitGetAsEntry(),l)):l.push(o);
e.when.apply(e,f).done(function(){l.length&&n(l)})},_traverseDirectoryTree:function(t,n){var r=e.Deferred(),i=this;return t.isFile?t.file(function(e){n.push(e),r.resolve()
}):t.isDirectory&&t.createReader().readEntries(function(t){var o,a=t.length,s=[],u=[];for(o=0;a>o;o++)s.push(i._traverseDirectoryTree(t[o],u));
e.when.apply(e,s).then(function(){n.push.apply(n,u),r.resolve()},r.reject)}),r.promise()},destroy:function(){var e=this.elem;
e&&(e.off("dragenter",this.dragEnterHandler),e.off("dragover",this.dragOverHandler),e.off("dragleave",this.dragLeaveHandler),e.off("drop",this.dropHandler),this.options.disableGlobalDnd&&(r(document).off("dragover",this.dragOverHandler),r(document).off("drop",this.dropHandler)))
}})}),t("runtime/html5/filepaste",["base","runtime/html5/runtime","lib/file"],function(e,t,n){return t.register("FilePaste",{init:function(){var t,n,r,i,o=this.options,a=this.elem=o.container,s=".*";
if(o.accept){for(t=[],n=0,r=o.accept.length;r>n;n++)i=o.accept[n].mimeTypes,i&&t.push(i);t.length&&(s=t.join(","),s=s.replace(/,/g,"|").replace(/\*/g,".*"))
}this.accept=s=new RegExp(s,"i"),this.hander=e.bindFn(this._pasteHander,this),a.on("paste",this.hander)},_pasteHander:function(e){var t,r,i,o,a,s=[],u=this.getRuid();
for(e=e.originalEvent||e,t=e.clipboardData.items,o=0,a=t.length;a>o;o++)r=t[o],"file"===r.kind&&(i=r.getAsFile())&&s.push(new n(u,i));
s.length&&(e.preventDefault(),e.stopPropagation(),this.trigger("paste",s))},destroy:function(){this.elem.off("paste",this.hander)
}})}),t("runtime/html5/filepicker",["base","runtime/html5/runtime"],function(e,t){var n=e.$;return t.register("FilePicker",{init:function(){var e,t,r,i,o=this.getRuntime().getContainer(),a=this,s=a.owner,u=a.options,c=this.label=n(document.createElement("label")),l=this.input=n(document.createElement("input"));
if(l.attr("type","file"),l.attr("name",u.name),l.addClass("webuploader-element-invisible"),c.on("click",function(){l.trigger("click")
}),c.css({opacity:0,width:"100%",height:"100%",display:"block",cursor:"pointer",background:"#ffffff"}),u.multiple&&l.attr("multiple","multiple"),u.accept&&u.accept.length>0){for(e=[],t=0,r=u.accept.length;r>t;t++)e.push(u.accept[t].mimeTypes);
l.attr("accept",e.join(","))}o.append(l),o.append(c),i=function(e){s.trigger(e.type)},l.on("change",function(e){var t,r=arguments.callee;
a.files=e.target.files,t=this.cloneNode(!0),t.value=null,this.parentNode.replaceChild(t,this),l.off(),l=n(t).on("change",r).on("mouseenter mouseleave",i),s.trigger("change")
}),c.on("mouseenter mouseleave",i)},getFiles:function(){return this.files},destroy:function(){this.input.off(),this.label.off()
}})}),t("runtime/html5/util",["base"],function(t){var n=e.createObjectURL&&e||e.URL&&URL.revokeObjectURL&&URL||e.webkitURL,r=t.noop,i=r;
return n&&(r=function(){return n.createObjectURL.apply(n,arguments)},i=function(){return n.revokeObjectURL.apply(n,arguments)
}),{createObjectURL:r,revokeObjectURL:i,dataURL2Blob:function(e){var t,n,r,i,o,a;for(a=e.split(","),t=~a[0].indexOf("base64")?atob(a[1]):decodeURIComponent(a[1]),r=new ArrayBuffer(t.length),n=new Uint8Array(r),i=0;i<t.length;i++)n[i]=t.charCodeAt(i);
return o=a[0].split(":")[1].split(";")[0],this.arrayBufferToBlob(r,o)},dataURL2ArrayBuffer:function(e){var t,n,r,i;for(i=e.split(","),t=~i[0].indexOf("base64")?atob(i[1]):decodeURIComponent(i[1]),n=new Uint8Array(t.length),r=0;r<t.length;r++)n[r]=t.charCodeAt(r);
return n.buffer},arrayBufferToBlob:function(t,n){var r,i=e.BlobBuilder||e.WebKitBlobBuilder;return i?(r=new i,r.append(t),r.getBlob(n)):new Blob([t],n?{type:n}:{})
},canvasToDataUrl:function(e,t,n){return e.toDataURL(t,n/100)},parseMeta:function(e,t){t(!1,{})},updateImageHead:function(e){return e
}}}),t("runtime/html5/imagemeta",["runtime/html5/util"],function(e){var t;return t={parsers:{65505:[]},maxMetaDataSize:262144,parse:function(e,t){var n=this,r=new FileReader;
r.onload=function(){t(!1,n._parse(this.result)),r=r.onload=r.onerror=null},r.onerror=function(e){t(e.message),r=r.onload=r.onerror=null
},e=e.slice(0,n.maxMetaDataSize),r.readAsArrayBuffer(e.getSource())},_parse:function(e,n){if(!(e.byteLength<6)){var r,i,o,a,s=new DataView(e),u=2,c=s.byteLength-4,l=u,f={};
if(65496===s.getUint16(0)){for(;c>u&&(r=s.getUint16(u),r>=65504&&65519>=r||65534===r)&&(i=s.getUint16(u+2)+2,!(u+i>s.byteLength));){if(o=t.parsers[r],!n&&o)for(a=0;a<o.length;a+=1)o[a].call(t,s,u,i,f);
u+=i,l=u}l>6&&(f.imageHead=e.slice?e.slice(2,l):new Uint8Array(e).subarray(2,l))}return f}},updateImageHead:function(e,t){var n,r,i,o=this._parse(e,!0);
return i=2,o.imageHead&&(i=2+o.imageHead.byteLength),r=e.slice?e.slice(i):new Uint8Array(e).subarray(i),n=new Uint8Array(t.byteLength+2+r.byteLength),n[0]=255,n[1]=216,n.set(new Uint8Array(t),2),n.set(new Uint8Array(r),t.byteLength+2),n.buffer
}},e.parseMeta=function(){return t.parse.apply(t,arguments)},e.updateImageHead=function(){return t.updateImageHead.apply(t,arguments)
},t}),t("runtime/html5/imagemeta/exif",["base","runtime/html5/imagemeta"],function(e,t){var n={};return n.ExifMap=function(){return this
},n.ExifMap.prototype.map={Orientation:274},n.ExifMap.prototype.get=function(e){return this[e]||this[this.map[e]]},n.exifTagTypes={1:{getValue:function(e,t){return e.getUint8(t)
},size:1},2:{getValue:function(e,t){return String.fromCharCode(e.getUint8(t))},size:1,ascii:!0},3:{getValue:function(e,t,n){return e.getUint16(t,n)
},size:2},4:{getValue:function(e,t,n){return e.getUint32(t,n)},size:4},5:{getValue:function(e,t,n){return e.getUint32(t,n)/e.getUint32(t+4,n)
},size:8},9:{getValue:function(e,t,n){return e.getInt32(t,n)},size:4},10:{getValue:function(e,t,n){return e.getInt32(t,n)/e.getInt32(t+4,n)
},size:8}},n.exifTagTypes[7]=n.exifTagTypes[1],n.getExifValue=function(t,r,i,o,a,s){var u,c,l,f,h,d,p=n.exifTagTypes[o];if(!p)return void e.log("Invalid Exif data: Invalid tag type.");
if(u=p.size*a,c=u>4?r+t.getUint32(i+8,s):i+8,c+u>t.byteLength)return void e.log("Invalid Exif data: Invalid data offset.");
if(1===a)return p.getValue(t,c,s);for(l=[],f=0;a>f;f+=1)l[f]=p.getValue(t,c+f*p.size,s);if(p.ascii){for(h="",f=0;f<l.length&&(d=l[f],"\x00"!==d);f+=1)h+=d;
return h}return l},n.parseExifTag=function(e,t,r,i,o){var a=e.getUint16(r,i);o.exif[a]=n.getExifValue(e,t,r,e.getUint16(r+2,i),e.getUint32(r+4,i),i)
},n.parseExifTags=function(t,n,r,i,o){var a,s,u;if(r+6>t.byteLength)return void e.log("Invalid Exif data: Invalid directory offset.");
if(a=t.getUint16(r,i),s=r+2+12*a,s+4>t.byteLength)return void e.log("Invalid Exif data: Invalid directory size.");for(u=0;a>u;u+=1)this.parseExifTag(t,n,r+2+12*u,i,o);
return t.getUint32(s,i)},n.parseExifData=function(t,r,i,o){var a,s,u=r+10;if(1165519206===t.getUint32(r+4)){if(u+8>t.byteLength)return void e.log("Invalid Exif data: Invalid segment size.");
if(0!==t.getUint16(r+8))return void e.log("Invalid Exif data: Missing byte alignment offset.");switch(t.getUint16(u)){case 18761:a=!0;
break;case 19789:a=!1;break;default:return void e.log("Invalid Exif data: Invalid byte alignment marker.")}if(42!==t.getUint16(u+2,a))return void e.log("Invalid Exif data: Missing TIFF marker.");
s=t.getUint32(u+4,a),o.exif=new n.ExifMap,s=n.parseExifTags(t,u,u+s,a,o)}},t.parsers[65505].push(n.parseExifData),n}),t("runtime/html5/jpegencoder",[],function(){function e(e){function t(e){for(var t=[16,11,10,16,24,40,51,61,12,12,14,19,26,58,60,55,14,13,16,24,40,57,69,56,14,17,22,29,51,87,80,62,18,22,37,56,68,109,103,77,24,35,55,64,81,104,113,92,49,64,78,87,103,121,120,101,72,92,95,98,112,100,103,99],n=0;64>n;n++){var r=E((t[n]*e+50)/100);
1>r?r=1:r>255&&(r=255),k[M[n]]=r}for(var i=[17,18,24,47,99,99,99,99,18,21,26,66,99,99,99,99,24,26,56,99,99,99,99,99,47,66,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99],o=0;64>o;o++){var a=E((i[o]*e+50)/100);
1>a?a=1:a>255&&(a=255),S[M[o]]=a}for(var s=[1,1.387039845,1.306562965,1.175875602,1,.785694958,.5411961,.275899379],u=0,c=0;8>c;c++)for(var l=0;8>l;l++)T[u]=1/(k[M[u]]*s[c]*s[l]*8),F[u]=1/(S[M[u]]*s[c]*s[l]*8),u++
}function n(e,t){for(var n=0,r=0,i=new Array,o=1;16>=o;o++){for(var a=1;a<=e[o];a++)i[t[r]]=[],i[t[r]][0]=n,i[t[r]][1]=o,r++,n++;
n*=2}return i}function r(){_=n(j,N),y=n(V,G),w=n($,Q),x=n(J,W)}function i(){for(var e=1,t=2,n=1;15>=n;n++){for(var r=e;t>r;r++)U[32767+r]=n,A[32767+r]=[],A[32767+r][1]=n,A[32767+r][0]=r;
for(var i=-(t-1);-e>=i;i++)U[32767+i]=n,A[32767+i]=[],A[32767+i][1]=n,A[32767+i][0]=t-1+i;e<<=1,t<<=1}}function o(){for(var e=0;256>e;e++)z[e]=19595*e,z[e+256>>0]=38470*e,z[e+512>>0]=7471*e+32768,z[e+768>>0]=-11059*e,z[e+1024>>0]=-21709*e,z[e+1280>>0]=32768*e+8421375,z[e+1536>>0]=-27439*e,z[e+1792>>0]=-5329*e
}function a(e){for(var t=e[0],n=e[1]-1;n>=0;)t&1<<n&&(C|=1<<q),n--,q--,0>q&&(255==C?(s(255),s(0)):s(C),q=7,C=0)}function s(e){I.push(H[e])
}function u(e){s(e>>8&255),s(255&e)}function c(e,t){var n,r,i,o,a,s,u,c,l,f=0,h=8,d=64;for(l=0;h>l;++l){n=e[f],r=e[f+1],i=e[f+2],o=e[f+3],a=e[f+4],s=e[f+5],u=e[f+6],c=e[f+7];
var p=n+c,g=n-c,m=r+u,v=r-u,b=i+s,_=i-s,y=o+a,w=o-a,x=p+y,R=p-y,E=m+b,k=m-b;e[f]=x+E,e[f+4]=x-E;var S=.707106781*(k+R);e[f+2]=R+S,e[f+6]=R-S,x=w+_,E=_+v,k=v+g;
var T=.382683433*(x-k),F=.5411961*x+T,A=1.306562965*k+T,U=.707106781*E,O=g+U,I=g-U;e[f+5]=I+F,e[f+3]=I-F,e[f+1]=O+A,e[f+7]=O-A,f+=8
}for(f=0,l=0;h>l;++l){n=e[f],r=e[f+8],i=e[f+16],o=e[f+24],a=e[f+32],s=e[f+40],u=e[f+48],c=e[f+56];var C=n+c,q=n-c,B=r+u,L=r-u,P=i+s,H=i-s,z=o+a,M=o-a,j=C+z,N=C-z,$=B+P,Q=B-P;
e[f]=j+$,e[f+32]=j-$;var V=.707106781*(Q+N);e[f+16]=N+V,e[f+48]=N-V,j=M+H,$=H+L,Q=L+q;var G=.382683433*(j-Q),J=.5411961*j+G,W=1.306562965*Q+G,X=.707106781*$,Z=q+X,K=q-X;
e[f+40]=K+J,e[f+24]=K-J,e[f+8]=Z+W,e[f+56]=Z-W,f++}var Y;for(l=0;d>l;++l)Y=e[l]*t[l],D[l]=Y>0?Y+.5|0:Y-.5|0;return D}function l(){u(65504),u(16),s(74),s(70),s(73),s(70),s(0),s(1),s(1),s(0),u(1),u(1),s(0),s(0)
}function f(e,t){u(65472),u(17),s(8),u(t),u(e),s(3),s(1),s(17),s(0),s(2),s(17),s(1),s(3),s(17),s(1)}function h(){u(65499),u(132),s(0);
for(var e=0;64>e;e++)s(k[e]);s(1);for(var t=0;64>t;t++)s(S[t])}function d(){u(65476),u(418),s(0);for(var e=0;16>e;e++)s(j[e+1]);
for(var t=0;11>=t;t++)s(N[t]);s(16);for(var n=0;16>n;n++)s($[n+1]);for(var r=0;161>=r;r++)s(Q[r]);s(1);for(var i=0;16>i;i++)s(V[i+1]);
for(var o=0;11>=o;o++)s(G[o]);s(17);for(var a=0;16>a;a++)s(J[a+1]);for(var c=0;161>=c;c++)s(W[c])}function p(){u(65498),u(12),s(3),s(1),s(0),s(2),s(17),s(3),s(17),s(0),s(63),s(0)
}function g(e,t,n,r,i){for(var o,s=i[0],u=i[240],l=16,f=63,h=64,d=c(e,t),p=0;h>p;++p)O[M[p]]=d[p];var g=O[0]-n;n=O[0],0==g?a(r[0]):(o=32767+g,a(r[U[o]]),a(A[o]));
for(var m=63;m>0&&0==O[m];m--);if(0==m)return a(s),n;for(var v,b=1;m>=b;){for(var _=b;0==O[b]&&m>=b;++b);var y=b-_;if(y>=l){v=y>>4;
for(var w=1;v>=w;++w)a(u);y=15&y}o=32767+O[b],a(i[(y<<4)+U[o]]),a(A[o]),b++}return m!=f&&a(s),n}function m(){for(var e=String.fromCharCode,t=0;256>t;t++)H[t]=e(t)
}function v(e){if(0>=e&&(e=1),e>100&&(e=100),R!=e){var n=0;n=Math.floor(50>e?5e3/e:200-2*e),t(n),R=e}}function b(){e||(e=50),m(),r(),i(),o(),v(e)
}var _,y,w,x,R,E=(Math.round,Math.floor),k=new Array(64),S=new Array(64),T=new Array(64),F=new Array(64),A=new Array(65535),U=new Array(65535),D=new Array(64),O=new Array(64),I=[],C=0,q=7,B=new Array(64),L=new Array(64),P=new Array(64),H=new Array(256),z=new Array(2048),M=[0,1,5,6,14,15,27,28,2,4,7,13,16,26,29,42,3,8,12,17,25,30,41,43,9,11,18,24,31,40,44,53,10,19,23,32,39,45,52,54,20,22,33,38,46,51,55,60,21,34,37,47,50,56,59,61,35,36,48,49,57,58,62,63],j=[0,0,1,5,1,1,1,1,1,1,0,0,0,0,0,0,0],N=[0,1,2,3,4,5,6,7,8,9,10,11],$=[0,0,2,1,3,3,2,4,3,5,5,4,4,0,0,1,125],Q=[1,2,3,0,4,17,5,18,33,49,65,6,19,81,97,7,34,113,20,50,129,145,161,8,35,66,177,193,21,82,209,240,36,51,98,114,130,9,10,22,23,24,25,26,37,38,39,40,41,42,52,53,54,55,56,57,58,67,68,69,70,71,72,73,74,83,84,85,86,87,88,89,90,99,100,101,102,103,104,105,106,115,116,117,118,119,120,121,122,131,132,133,134,135,136,137,138,146,147,148,149,150,151,152,153,154,162,163,164,165,166,167,168,169,170,178,179,180,181,182,183,184,185,186,194,195,196,197,198,199,200,201,202,210,211,212,213,214,215,216,217,218,225,226,227,228,229,230,231,232,233,234,241,242,243,244,245,246,247,248,249,250],V=[0,0,3,1,1,1,1,1,1,1,1,1,0,0,0,0,0],G=[0,1,2,3,4,5,6,7,8,9,10,11],J=[0,0,2,1,2,4,4,3,4,7,5,4,4,0,1,2,119],W=[0,1,2,3,17,4,5,33,49,6,18,65,81,7,97,113,19,34,50,129,8,20,66,145,161,177,193,9,35,51,82,240,21,98,114,209,10,22,36,52,225,37,241,23,24,25,26,38,39,40,41,42,53,54,55,56,57,58,67,68,69,70,71,72,73,74,83,84,85,86,87,88,89,90,99,100,101,102,103,104,105,106,115,116,117,118,119,120,121,122,130,131,132,133,134,135,136,137,138,146,147,148,149,150,151,152,153,154,162,163,164,165,166,167,168,169,170,178,179,180,181,182,183,184,185,186,194,195,196,197,198,199,200,201,202,210,211,212,213,214,215,216,217,218,226,227,228,229,230,231,232,233,234,242,243,244,245,246,247,248,249,250];
this.encode=function(e,t){t&&v(t),I=new Array,C=0,q=7,u(65496),l(),h(),f(e.width,e.height),d(),p();var n=0,r=0,i=0;C=0,q=7,this.encode.displayName="_encode_";
for(var o,s,c,m,b,R,E,k,S,A=e.data,U=e.width,D=e.height,O=4*U,H=0;D>H;){for(o=0;O>o;){for(b=O*H+o,R=b,E=-1,k=0,S=0;64>S;S++)k=S>>3,E=4*(7&S),R=b+k*O+E,H+k>=D&&(R-=O*(H+1+k-D)),o+E>=O&&(R-=o+E-O+4),s=A[R++],c=A[R++],m=A[R++],B[S]=(z[s]+z[c+256>>0]+z[m+512>>0]>>16)-128,L[S]=(z[s+768>>0]+z[c+1024>>0]+z[m+1280>>0]>>16)-128,P[S]=(z[s+1280>>0]+z[c+1536>>0]+z[m+1792>>0]>>16)-128;
n=g(B,T,n,_,w),r=g(L,F,r,y,x),i=g(P,F,i,y,x),o+=32}H+=8}if(q>=0){var M=[];M[1]=q+1,M[0]=(1<<q+1)-1,a(M)}u(65497);var j="data:image/jpeg;base64,"+btoa(I.join(""));
return I=[],j},b()}return e.encode=function(t,n){var r=new e(n);return r.encode(t)},e}),t("runtime/html5/androidpatch",["runtime/html5/util","runtime/html5/jpegencoder","base"],function(e,t,n){var r,i=e.canvasToDataUrl;
e.canvasToDataUrl=function(e,o,a){var s,u,c,l,f;return n.os.android?("image/jpeg"===o&&"undefined"==typeof r&&(l=i.apply(null,arguments),f=l.split(","),l=~f[0].indexOf("base64")?atob(f[1]):decodeURIComponent(f[1]),l=l.substring(0,2),r=255===l.charCodeAt(0)&&216===l.charCodeAt(1)),"image/jpeg"!==o||r?i.apply(null,arguments):(u=e.width,c=e.height,s=e.getContext("2d"),t.encode(s.getImageData(0,0,u,c),a))):i.apply(null,arguments)
}}),t("runtime/html5/image",["base","runtime/html5/runtime","runtime/html5/util"],function(e,t,n){var r="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs%3D";
return t.register("Image",{modified:!1,init:function(){var e=this,t=new Image;t.onload=function(){e._info={type:e.type,width:this.width,height:this.height},e._metas||"image/jpeg"!==e.type?e.owner.trigger("load"):n.parseMeta(e._blob,function(t,n){e._metas=n,e.owner.trigger("load")
})},t.onerror=function(){e.owner.trigger("error")},e._img=t},loadFromBlob:function(e){var t=this,r=t._img;t._blob=e,t.type=e.type,r.src=n.createObjectURL(e.getSource()),t.owner.once("load",function(){n.revokeObjectURL(r.src)
})},resize:function(e,t){var n=this._canvas||(this._canvas=document.createElement("canvas"));this._resize(this._img,n,e,t),this._blob=null,this.modified=!0,this.owner.trigger("complete","resize")
},crop:function(e,t,n,r,i){var o=this._canvas||(this._canvas=document.createElement("canvas")),a=this.options,s=this._img,u=s.naturalWidth,c=s.naturalHeight,l=this.getOrientation();
i=i||1,o.width=n,o.height=r,a.preserveHeaders||this._rotate2Orientaion(o,l),this._renderImageToCanvas(o,s,-e,-t,u*i,c*i),this._blob=null,this.modified=!0,this.owner.trigger("complete","crop")
},getAsBlob:function(e){var t,r=this._blob,i=this.options;if(e=e||this.type,this.modified||this.type!==e){if(t=this._canvas,"image/jpeg"===e){if(r=n.canvasToDataUrl(t,e,i.quality),i.preserveHeaders&&this._metas&&this._metas.imageHead)return r=n.dataURL2ArrayBuffer(r),r=n.updateImageHead(r,this._metas.imageHead),r=n.arrayBufferToBlob(r,e)
}else r=n.canvasToDataUrl(t,e);r=n.dataURL2Blob(r)}return r},getAsDataUrl:function(e){var t=this.options;return e=e||this.type,"image/jpeg"===e?n.canvasToDataUrl(this._canvas,e,t.quality):this._canvas.toDataURL(e)
},getOrientation:function(){return this._metas&&this._metas.exif&&this._metas.exif.get("Orientation")||1},info:function(e){return e?(this._info=e,this):this._info
},meta:function(e){return e?(this._meta=e,this):this._meta},destroy:function(){var e=this._canvas;this._img.onload=null,e&&(e.getContext("2d").clearRect(0,0,e.width,e.height),e.width=e.height=0,this._canvas=null),this._img.src=r,this._img=this._blob=null
},_resize:function(e,t,n,r){var i,o,a,s,u,c=this.options,l=e.width,f=e.height,h=this.getOrientation();~[5,6,7,8].indexOf(h)&&(n^=r,r^=n,n^=r),i=Math[c.crop?"max":"min"](n/l,r/f),c.allowMagnify||(i=Math.min(1,i)),o=l*i,a=f*i,c.crop?(t.width=n,t.height=r):(t.width=o,t.height=a),s=(t.width-o)/2,u=(t.height-a)/2,c.preserveHeaders||this._rotate2Orientaion(t,h),this._renderImageToCanvas(t,e,s,u,o,a)
},_rotate2Orientaion:function(e,t){var n=e.width,r=e.height,i=e.getContext("2d");switch(t){case 5:case 6:case 7:case 8:e.width=r,e.height=n
}switch(t){case 2:i.translate(n,0),i.scale(-1,1);break;case 3:i.translate(n,r),i.rotate(Math.PI);break;case 4:i.translate(0,r),i.scale(1,-1);
break;case 5:i.rotate(.5*Math.PI),i.scale(1,-1);break;case 6:i.rotate(.5*Math.PI),i.translate(0,-r);break;case 7:i.rotate(.5*Math.PI),i.translate(n,-r),i.scale(-1,1);
break;case 8:i.rotate(-.5*Math.PI),i.translate(-n,0)}},_renderImageToCanvas:function(){function t(e,t,n){var r,i,o,a=document.createElement("canvas"),s=a.getContext("2d"),u=0,c=n,l=n;
for(a.width=1,a.height=n,s.drawImage(e,0,0),r=s.getImageData(0,0,1,n).data;l>u;)i=r[4*(l-1)+3],0===i?c=l:u=l,l=c+u>>1;return o=l/n,0===o?1:o
}function n(e){var t,n,r=e.naturalWidth,i=e.naturalHeight;return r*i>1048576?(t=document.createElement("canvas"),t.width=t.height=1,n=t.getContext("2d"),n.drawImage(e,-r+1,0),0===n.getImageData(0,0,1,1).data[3]):!1
}return e.os.ios?e.os.ios>=7?function(e,n,r,i,o,a){var s=n.naturalWidth,u=n.naturalHeight,c=t(n,s,u);return e.getContext("2d").drawImage(n,0,0,s*c,u*c,r,i,o,a)
}:function(e,r,i,o,a,s){var u,c,l,f,h,d,p,g=r.naturalWidth,m=r.naturalHeight,v=e.getContext("2d"),b=n(r),_="image/jpeg"===this.type,y=1024,w=0,x=0;
for(b&&(g/=2,m/=2),v.save(),u=document.createElement("canvas"),u.width=u.height=y,c=u.getContext("2d"),l=_?t(r,g,m):1,f=Math.ceil(y*a/g),h=Math.ceil(y*s/m/l);m>w;){for(d=0,p=0;g>d;)c.clearRect(0,0,y,y),c.drawImage(r,-d,-w),v.drawImage(u,0,0,y,y,i+p,o+x,f,h),d+=y,p+=f;
w+=y,x+=h}v.restore(),u=c=null}:function(t){var n=e.slice(arguments,1),r=t.getContext("2d");r.drawImage.apply(r,n)}}()})}),t("runtime/html5/transport",["base","runtime/html5/runtime"],function(e,t){var n=e.noop,r=e.$;
return t.register("Transport",{init:function(){this._status=0,this._response=null},send:function(){var t,n,i,o=this.owner,a=this.options,s=this._initAjax(),u=o._blob,c=a.server;
a.sendAsBinary?(c+=(/\?/.test(c)?"&":"?")+r.param(o._formData),n=u.getSource()):(t=new FormData,r.each(o._formData,function(e,n){t.append(e,n)
}),t.append(a.fileVal,u.getSource(),a.filename||o._formData.name||"")),a.withCredentials&&"withCredentials"in s?(s.open(a.method,c,!0),s.withCredentials=!0):s.open(a.method,c),this._setRequestHeader(s,a.headers),n?(s.overrideMimeType&&s.overrideMimeType("application/octet-stream"),e.os.android?(i=new FileReader,i.onload=function(){s.send(this.result),i=i.onload=null
},i.readAsArrayBuffer(n)):s.send(n)):s.send(t)},getResponse:function(){return this._response},getResponseAsJson:function(){return this._parseJson(this._response)
},getStatus:function(){return this._status},abort:function(){var e=this._xhr;e&&(e.upload.onprogress=n,e.onreadystatechange=n,e.abort(),this._xhr=e=null)
},destroy:function(){this.abort()},_initAjax:function(){var e=this,t=new XMLHttpRequest,r=this.options;return!r.withCredentials||"withCredentials"in t||"undefined"==typeof XDomainRequest||(t=new XDomainRequest),t.upload.onprogress=function(t){var n=0;
return t.lengthComputable&&(n=t.loaded/t.total),e.trigger("progress",n)},t.onreadystatechange=function(){return 4===t.readyState?(t.upload.onprogress=n,t.onreadystatechange=n,e._xhr=null,e._status=t.status,t.status>=200&&t.status<300?(e._response=t.responseText,e.trigger("load")):t.status>=500&&t.status<600?(e._response=t.responseText,e.trigger("error","server")):e.trigger("error",e._status?"http":"abort")):void 0
},e._xhr=t,t},_setRequestHeader:function(e,t){r.each(t,function(t,n){e.setRequestHeader(t,n)})},_parseJson:function(e){var t;
try{t=JSON.parse(e)}catch(n){t={}}return t}})}),t("runtime/html5/md5",["runtime/html5/runtime"],function(e){var t=function(e,t){return e+t&4294967295
},n=function(e,n,r,i,o,a){return n=t(t(n,e),t(i,a)),t(n<<o|n>>>32-o,r)},r=function(e,t,r,i,o,a,s){return n(t&r|~t&i,e,t,o,a,s)
},i=function(e,t,r,i,o,a,s){return n(t&i|r&~i,e,t,o,a,s)},o=function(e,t,r,i,o,a,s){return n(t^r^i,e,t,o,a,s)},a=function(e,t,r,i,o,a,s){return n(r^(t|~i),e,t,o,a,s)
},s=function(e,n){var s=e[0],u=e[1],c=e[2],l=e[3];s=r(s,u,c,l,n[0],7,-680876936),l=r(l,s,u,c,n[1],12,-389564586),c=r(c,l,s,u,n[2],17,606105819),u=r(u,c,l,s,n[3],22,-1044525330),s=r(s,u,c,l,n[4],7,-176418897),l=r(l,s,u,c,n[5],12,1200080426),c=r(c,l,s,u,n[6],17,-1473231341),u=r(u,c,l,s,n[7],22,-45705983),s=r(s,u,c,l,n[8],7,1770035416),l=r(l,s,u,c,n[9],12,-1958414417),c=r(c,l,s,u,n[10],17,-42063),u=r(u,c,l,s,n[11],22,-1990404162),s=r(s,u,c,l,n[12],7,1804603682),l=r(l,s,u,c,n[13],12,-40341101),c=r(c,l,s,u,n[14],17,-1502002290),u=r(u,c,l,s,n[15],22,1236535329),s=i(s,u,c,l,n[1],5,-165796510),l=i(l,s,u,c,n[6],9,-1069501632),c=i(c,l,s,u,n[11],14,643717713),u=i(u,c,l,s,n[0],20,-373897302),s=i(s,u,c,l,n[5],5,-701558691),l=i(l,s,u,c,n[10],9,38016083),c=i(c,l,s,u,n[15],14,-660478335),u=i(u,c,l,s,n[4],20,-405537848),s=i(s,u,c,l,n[9],5,568446438),l=i(l,s,u,c,n[14],9,-1019803690),c=i(c,l,s,u,n[3],14,-187363961),u=i(u,c,l,s,n[8],20,1163531501),s=i(s,u,c,l,n[13],5,-1444681467),l=i(l,s,u,c,n[2],9,-51403784),c=i(c,l,s,u,n[7],14,1735328473),u=i(u,c,l,s,n[12],20,-1926607734),s=o(s,u,c,l,n[5],4,-378558),l=o(l,s,u,c,n[8],11,-2022574463),c=o(c,l,s,u,n[11],16,1839030562),u=o(u,c,l,s,n[14],23,-35309556),s=o(s,u,c,l,n[1],4,-1530992060),l=o(l,s,u,c,n[4],11,1272893353),c=o(c,l,s,u,n[7],16,-155497632),u=o(u,c,l,s,n[10],23,-1094730640),s=o(s,u,c,l,n[13],4,681279174),l=o(l,s,u,c,n[0],11,-358537222),c=o(c,l,s,u,n[3],16,-722521979),u=o(u,c,l,s,n[6],23,76029189),s=o(s,u,c,l,n[9],4,-640364487),l=o(l,s,u,c,n[12],11,-421815835),c=o(c,l,s,u,n[15],16,530742520),u=o(u,c,l,s,n[2],23,-995338651),s=a(s,u,c,l,n[0],6,-198630844),l=a(l,s,u,c,n[7],10,1126891415),c=a(c,l,s,u,n[14],15,-1416354905),u=a(u,c,l,s,n[5],21,-57434055),s=a(s,u,c,l,n[12],6,1700485571),l=a(l,s,u,c,n[3],10,-1894986606),c=a(c,l,s,u,n[10],15,-1051523),u=a(u,c,l,s,n[1],21,-2054922799),s=a(s,u,c,l,n[8],6,1873313359),l=a(l,s,u,c,n[15],10,-30611744),c=a(c,l,s,u,n[6],15,-1560198380),u=a(u,c,l,s,n[13],21,1309151649),s=a(s,u,c,l,n[4],6,-145523070),l=a(l,s,u,c,n[11],10,-1120210379),c=a(c,l,s,u,n[2],15,718787259),u=a(u,c,l,s,n[9],21,-343485551),e[0]=t(s,e[0]),e[1]=t(u,e[1]),e[2]=t(c,e[2]),e[3]=t(l,e[3])
},u=function(e){var t,n=[];for(t=0;64>t;t+=4)n[t>>2]=e.charCodeAt(t)+(e.charCodeAt(t+1)<<8)+(e.charCodeAt(t+2)<<16)+(e.charCodeAt(t+3)<<24);
return n},c=function(e){var t,n=[];for(t=0;64>t;t+=4)n[t>>2]=e[t]+(e[t+1]<<8)+(e[t+2]<<16)+(e[t+3]<<24);return n},l=function(e){var t,n,r,i,o,a,c=e.length,l=[1732584193,-271733879,-1732584194,271733878];
for(t=64;c>=t;t+=64)s(l,u(e.substring(t-64,t)));for(e=e.substring(t-64),n=e.length,r=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],t=0;n>t;t+=1)r[t>>2]|=e.charCodeAt(t)<<(t%4<<3);
if(r[t>>2]|=128<<(t%4<<3),t>55)for(s(l,r),t=0;16>t;t+=1)r[t]=0;return i=8*c,i=i.toString(16).match(/(.*?)(.{0,8})$/),o=parseInt(i[2],16),a=parseInt(i[1],16)||0,r[14]=o,r[15]=a,s(l,r),l
},f=function(e){var t,n,r,i,o,a,u=e.length,l=[1732584193,-271733879,-1732584194,271733878];for(t=64;u>=t;t+=64)s(l,c(e.subarray(t-64,t)));
for(e=u>t-64?e.subarray(t-64):new Uint8Array(0),n=e.length,r=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],t=0;n>t;t+=1)r[t>>2]|=e[t]<<(t%4<<3);
if(r[t>>2]|=128<<(t%4<<3),t>55)for(s(l,r),t=0;16>t;t+=1)r[t]=0;return i=8*u,i=i.toString(16).match(/(.*?)(.{0,8})$/),o=parseInt(i[2],16),a=parseInt(i[1],16)||0,r[14]=o,r[15]=a,s(l,r),l
},h=["0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"],d=function(e){var t,n="";for(t=0;4>t;t+=1)n+=h[e>>8*t+4&15]+h[e>>8*t&15];
return n},p=function(e){var t;for(t=0;t<e.length;t+=1)e[t]=d(e[t]);return e.join("")},g=function(e){return p(l(e))},m=function(){this.reset()
};return"5d41402abc4b2a76b9719d911017c592"!==g("hello")&&(t=function(e,t){var n=(65535&e)+(65535&t),r=(e>>16)+(t>>16)+(n>>16);
return r<<16|65535&n}),m.prototype.append=function(e){return/[\u0080-\uFFFF]/.test(e)&&(e=unescape(encodeURIComponent(e))),this.appendBinary(e),this
},m.prototype.appendBinary=function(e){this._buff+=e,this._length+=e.length;var t,n=this._buff.length;for(t=64;n>=t;t+=64)s(this._state,u(this._buff.substring(t-64,t)));
return this._buff=this._buff.substr(t-64),this},m.prototype.end=function(e){var t,n,r=this._buff,i=r.length,o=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
for(t=0;i>t;t+=1)o[t>>2]|=r.charCodeAt(t)<<(t%4<<3);return this._finish(o,i),n=e?this._state:p(this._state),this.reset(),n
},m.prototype._finish=function(e,t){var n,r,i,o=t;if(e[o>>2]|=128<<(o%4<<3),o>55)for(s(this._state,e),o=0;16>o;o+=1)e[o]=0;
n=8*this._length,n=n.toString(16).match(/(.*?)(.{0,8})$/),r=parseInt(n[2],16),i=parseInt(n[1],16)||0,e[14]=r,e[15]=i,s(this._state,e)
},m.prototype.reset=function(){return this._buff="",this._length=0,this._state=[1732584193,-271733879,-1732584194,271733878],this
},m.prototype.destroy=function(){delete this._state,delete this._buff,delete this._length},m.hash=function(e,t){/[\u0080-\uFFFF]/.test(e)&&(e=unescape(encodeURIComponent(e)));
var n=l(e);return t?n:p(n)},m.hashBinary=function(e,t){var n=l(e);return t?n:p(n)},m.ArrayBuffer=function(){this.reset()},m.ArrayBuffer.prototype.append=function(e){var t,n=this._concatArrayBuffer(this._buff,e),r=n.length;
for(this._length+=e.byteLength,t=64;r>=t;t+=64)s(this._state,c(n.subarray(t-64,t)));return this._buff=r>t-64?n.subarray(t-64):new Uint8Array(0),this
},m.ArrayBuffer.prototype.end=function(e){var t,n,r=this._buff,i=r.length,o=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];for(t=0;i>t;t+=1)o[t>>2]|=r[t]<<(t%4<<3);
return this._finish(o,i),n=e?this._state:p(this._state),this.reset(),n},m.ArrayBuffer.prototype._finish=m.prototype._finish,m.ArrayBuffer.prototype.reset=function(){return this._buff=new Uint8Array(0),this._length=0,this._state=[1732584193,-271733879,-1732584194,271733878],this
},m.ArrayBuffer.prototype.destroy=m.prototype.destroy,m.ArrayBuffer.prototype._concatArrayBuffer=function(e,t){var n=e.length,r=new Uint8Array(n+t.byteLength);
return r.set(e),r.set(new Uint8Array(t),n),r},m.ArrayBuffer.hash=function(e,t){var n=f(new Uint8Array(e));return t?n:p(n)
},e.register("Md5",{init:function(){},loadFromBlob:function(e){var t,n,r=e.getSource(),i=2097152,o=Math.ceil(r.size/i),a=0,s=this.owner,u=new m.ArrayBuffer,c=this,l=r.mozSlice||r.webkitSlice||r.slice;
n=new FileReader,(t=function(){var f,h;f=a*i,h=Math.min(f+i,r.size),n.onload=function(t){u.append(t.target.result),s.trigger("progress",{total:e.size,loaded:h})
},n.onloadend=function(){n.onloadend=n.onload=null,++a<o?setTimeout(t,1):setTimeout(function(){s.trigger("load"),c.result=u.end(),t=e=r=u=null,s.trigger("complete")
},50)},n.readAsArrayBuffer(l.call(r,f,h))})()},getResult:function(){return this.result}})}),t("runtime/flash/runtime",["base","runtime/runtime","runtime/compbase"],function(t,n,r){function i(){var e;
try{e=navigator.plugins["Shockwave Flash"],e=e.description}catch(t){try{e=new ActiveXObject("ShockwaveFlash.ShockwaveFlash").GetVariable("$version")
}catch(n){e="0.0"}}return e=e.match(/\d+/g),parseFloat(e[0]+"."+e[1],10)}function o(){function r(e,t){var n,r,i=e.type||e;
n=i.split("::"),r=n[0],i=n[1],"Ready"===i&&r===c.uid?c.trigger("ready"):o[r]&&o[r].trigger(i.toLowerCase(),e,t)}var i={},o={},a=this.destroy,c=this,l=t.guid("webuploader_");
n.apply(c,arguments),c.type=s,c.exec=function(e,n){var r,a=this,s=a.uid,l=t.slice(arguments,2);return o[s]=a,u[e]&&(i[s]||(i[s]=new u[e](a,c)),r=i[s],r[n])?r[n].apply(r,l):c.flashExec.apply(a,arguments)
},e[l]=function(){var e=arguments;setTimeout(function(){r.apply(null,e)},1)},this.jsreciver=l,this.destroy=function(){return a&&a.apply(this,arguments)
},this.flashExec=function(e,n){var r=c.getFlash(),i=t.slice(arguments,2);return r.exec(this.uid,e,n,i)}}var a=t.$,s="flash",u={};
return t.inherits(n,{constructor:o,init:function(){var e,n=this.getContainer(),r=this.options;n.css({position:"absolute",top:"-8px",left:"-8px",width:"9px",height:"9px",overflow:"hidden"}),e='<object id="'+this.uid+'" type="application/x-shockwave-flash" data="'+r.swf+'" ',t.browser.ie&&(e+='classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" '),e+='width="100%" height="100%" style="outline:0"><param name="movie" value="'+r.swf+'" /><param name="flashvars" value="uid='+this.uid+"&jsreciver="+this.jsreciver+'" /><param name="wmode" value="transparent" /><param name="allowscriptaccess" value="always" /></object>',n.html(e)
},getFlash:function(){return this._flash?this._flash:(this._flash=a("#"+this.uid).get(0),this._flash)}}),o.register=function(e,n){return n=u[e]=t.inherits(r,a.extend({flashExec:function(){var e=this.owner,t=this.getRuntime();
return t.flashExec.apply(e,arguments)}},n))},i()>=11.4&&n.addRuntime(s,o),o}),t("runtime/flash/filepicker",["base","runtime/flash/runtime"],function(e,t){var n=e.$;
return t.register("FilePicker",{init:function(e){var t,r,i=n.extend({},e);for(t=i.accept&&i.accept.length,r=0;t>r;r++)i.accept[r].title||(i.accept[r].title="Files");
delete i.button,delete i.id,delete i.container,this.flashExec("FilePicker","init",i)},destroy:function(){this.flashExec("FilePicker","destroy")
}})}),t("runtime/flash/image",["runtime/flash/runtime"],function(e){return e.register("Image",{loadFromBlob:function(e){var t=this.owner;
t.info()&&this.flashExec("Image","info",t.info()),t.meta()&&this.flashExec("Image","meta",t.meta()),this.flashExec("Image","loadFromBlob",e.uid)
}})}),t("runtime/flash/transport",["base","runtime/flash/runtime","runtime/client"],function(t,n,r){var i=t.$;return n.register("Transport",{init:function(){this._status=0,this._response=null,this._responseJson=null
},send:function(){var e,t=this.owner,n=this.options,r=this._initAjax(),o=t._blob,a=n.server;r.connectRuntime(o.ruid),n.sendAsBinary?(a+=(/\?/.test(a)?"&":"?")+i.param(t._formData),e=o.uid):(i.each(t._formData,function(e,t){r.exec("append",e,t)
}),r.exec("appendBlob",n.fileVal,o.uid,n.filename||t._formData.name||"")),this._setRequestHeader(r,n.headers),r.exec("send",{method:n.method,url:a,forceURLStream:n.forceURLStream,mimeType:"application/octet-stream"},e)
},getStatus:function(){return this._status},getResponse:function(){return this._response||""},getResponseAsJson:function(){return this._responseJson
},abort:function(){var e=this._xhr;e&&(e.exec("abort"),e.destroy(),this._xhr=e=null)},destroy:function(){this.abort()},_initAjax:function(){var t=this,n=new r("XMLHttpRequest");
return n.on("uploadprogress progress",function(e){var n=e.loaded/e.total;return n=Math.min(1,Math.max(0,n)),t.trigger("progress",n)
}),n.on("load",function(){var r,i=n.exec("getStatus"),o=!1,a="";return n.off(),t._xhr=null,i>=200&&300>i?o=!0:i>=500&&600>i?(o=!0,a="server"):a="http",o&&(t._response=n.exec("getResponse"),t._response=decodeURIComponent(t._response),r=e.JSON&&e.JSON.parse||function(e){try{return new Function("return "+e).call()
}catch(t){return{}}},t._responseJson=t._response?r(t._response):{}),n.destroy(),n=null,a?t.trigger("error",a):t.trigger("load")
}),n.on("error",function(){n.off(),t._xhr=null,t.trigger("error","http")}),t._xhr=n,n},_setRequestHeader:function(e,t){i.each(t,function(t,n){e.exec("setRequestHeader",t,n)
})}})}),t("runtime/flash/blob",["runtime/flash/runtime","lib/blob"],function(e,t){return e.register("Blob",{slice:function(e,n){var r=this.flashExec("Blob","slice",e,n);
return new t(r.uid,r)}})}),t("runtime/flash/md5",["runtime/flash/runtime"],function(e){return e.register("Md5",{init:function(){},loadFromBlob:function(e){return this.flashExec("Md5","loadFromBlob",e.uid)
}})}),t("preset/all",["base","widgets/filednd","widgets/filepaste","widgets/filepicker","widgets/image","widgets/queue","widgets/runtime","widgets/upload","widgets/validator","widgets/md5","runtime/html5/blob","runtime/html5/dnd","runtime/html5/filepaste","runtime/html5/filepicker","runtime/html5/imagemeta/exif","runtime/html5/androidpatch","runtime/html5/image","runtime/html5/transport","runtime/html5/md5","runtime/flash/filepicker","runtime/flash/image","runtime/flash/transport","runtime/flash/blob","runtime/flash/md5"],function(e){return e
}),t("widgets/log",["base","uploader","widgets/widget"],function(e,t){{var n=(e.$,(location.hostname||location.host||"protected").toLowerCase());
n&&/baidu/i.exec(n)}}),t("webuploader",["preset/all","widgets/log"],function(e){return e});var r=n;return r("webuploader")
})});
;define("translation:widget/common/util",function(t,e,r){"use strict";function n(t){if(Array.isArray(t)){for(var e=0,r=Array(t.length);e<t.length;e++)r[e]=t[e];
return r}return Array.from(t)}var o={};o.supportLocalStorage=function(){try{var t="test",e="testValue",r=window.localStorage;
r.setItem(t,e);var n=r.getItem(t);return r.removeItem(t),n===e}catch(o){return!1}}(),o.encodeHTML=function(t){return String(t).replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/"/g,"&quot;").replace(/'/g,"&#39;")
},o.decodeHTML=function(t){var e=String(t).replace(/&quot;/g,'"').replace(/&lt;/g,"<").replace(/&gt;/g,">").replace(/&amp;/g,"&");
return e.replace(/&#([\d]+);/g,function(t,e){return String.fromCharCode(parseInt(e,10))})},o.throttle=function(t,e,r){if(t._throttleId&&clearTimeout(t._throttleId),!r){var n=e?e:{};
t._throttleId=setTimeout(function(){t.call(n.context?n.context:null,n.params?n.params:null)},n.time?n.time:300)}},o.loadScript=function(t,e,r){var n=document.createElement("script");
e&&(n.id=e),n.type="text/javascript",n.src=t,document.body.appendChild(n),n.onload=n.onreadystatechange=function(){/undefined|loaded|complete/.test(this.readyState)&&r&&r(),n.onload=n.onreadystatechange=null
}},o.formatString=function(t,e){var r=/\$\$\{(\w+)\}/gi;return e?t.replace(r,function(t,r){return e.hasOwnProperty(r)?e[r]:"$$undefined$$"
}):t},o.isUrl=function(t){return/^(((http[s]?:\/\/)|(ftp:\/\/))?([\w-]+\.)+(com|edu|gov|int|mil|net|org|biz|info|pro|name|museum|coop|aero|xxx|idv|al|dz|af|ar|ae|aw|om|az|eg|et|ie|ee|ad|ao|ai|ag|at|au|mo|bb|pg|bs|pk|py|ps|bh|pa|br|by|bm|bg|mp|bj|be|is|pr|ba|pl|bo|bz|bw|bt|bf|bi|bv|kp|gq|dk|de|tl|tp|tg|dm|do|ru|ec|er|fr|fo|pf|gf|tf|va|ph|fj|fi|cv|fk|gm|cg|cd|co|cr|gg|gd|gl|ge|cu|gp|gu|gy|kz|ht|kr|nl|an|hm|hn|ki|dj|kg|gn|gw|ca|gh|ga|kh|cz|zw|cm|qa|ky|km|ci|kw|cc|hr|ke|ck|lv|ls|la|lb|lt|lr|ly|li|re|lu|rw|ro|mg|im|mv|mt|mw|my|ml|mk|mh|mq|yt|mu|mr|us|um|as|vi|mn|ms|bd|pe|fm|mm|md|ma|mc|mz|mx|nr|np|ni|ne|ng|nu|no|nf|na|za|zq|aq|gs|eu|pw|pn|pt|jp|se|ch|sv|ws|yu|sl|sn|cy|sc|sa|cx|st|sh|kn|lc|sm|pm|vc|lk|sk|si|sj|sz|sd|sr|sb|so|tj|tw|th|tz|to|tc|tt|tn|tv|tr|tm|tk|wf|vu|gt|ve|bn|ug|ua|uy|uz|es|eh|gr|hk|sg|nc|nz|hu|sy|jm|am|ac|ye|iq|ir|il|it|in|id|uk|vg|io|jo|vn|zm|je|td|gi|cl|cf|cn)(:\d+)?(\/[^\s]*)?)$/.test(t)
},o.getIEVersion=function(){var t,e=navigator.userAgent,r=!1;return/msie|trident/i.test(e)&&(t=e.match(/(?:msie |rv:)(\d+(\.\d+)?)/i),r=t&&t.length>1&&parseInt(t[1],10)),r
},o.getProtocol=function(){return location.protocol},o.getDisplayLength=function(t){return[].concat(n(t)).reduce(function(t,e){return t+(/^[\u0391-\uFFE5]+$/.test(e)?1:.5)
},0)},o.testCookie=function(){document.cookie="cookietest=1";var t=-1!==document.cookie.indexOf("cookietest=");return document.cookie="cookietest=1; expires=Thu, 01-Jan-1970 00:00:01 GMT",t
},r.exports=o});
;define("translation:widget/header/downloadGuide/downloadGuide",function(){"use strict";var n={init:function(){this.bindEvents()
},bindEvents:function(){$(".logolist-li").on("click",function(){var n=$(this).find("p").text();_hmt.push(["_trackEvent","首页",n+"插件图标点击下载"])
}),$(".ocr-extension-browser").on("click",function(){_hmt.push(["_trackEvent","首页","截图翻译插件图标点击下载"])}),$(".download-app").on("click",function(){_hmt.push(["_trackEvent","首页","下载翻译app点击"])
})}};$(function(){n.init()})});
;define("translation:widget/header/manualTrans/manualTrans",function(){"use strict";var n={init:function(){this.bindEvents()
},bindEvents:function(){$(".manual-trans-info .list-name").on("click",function(){_hmt.push(["_trackEvent","首页","web端点击顶部彩条上方的人工翻译按钮"])
}),$(".manual-trans-info .entry-trans-info .quick-trans").on("click",function(){_hmt.push(["_trackEvent","首页","web端点击顶部人工翻译hover浮层的快速翻译"])
}),$(".manual-trans-info .entry-trans-info .doc-trans").on("click",function(){_hmt.push(["_trackEvent","首页","web端点击顶部人工翻译hover浮层的文档翻译"])
})}};$(function(){n.init()})});
;define("translation:widget/login/login",function(n,o,t){"use strict";function e(n){n.returnValue=!1,r.hide(),c.set("account",{is_login:"1",user_name:n.rsp.data.userName,add_name:Boolean(n.rsp.data.userName)});
var o=s("tpl-user-info",c.get("account"));$(".header .navigation > ul").html(o)}function a(n){return new Promise(function(o){$("#passport-login-pop").remove(),n?(u.onLoginSuccess=function(n){e(n),o()
},r=passport.pop.init(u),r.show()):(delete u.onLoginSuccess,r=passport.pop.init(u),r.show(),o())})}function i(){return new Promise(function(n){c.get("account").is_login?n():a(!0).then(n)
})}n("translation:widget/common/config/global");var c=n("translation:widget/common/environment"),s=n("translation:widget/common/third_party/template"),r=null,u={tangram:!0,apiOpt:{product:"translate",staticPage:document.location.protocol+"//"+document.location.host+"/v3Jump.html",u:document.location.protocol+"//"+document.location.host+document.location.pathname}};
t.exports={newLoginInstanceAsync:a,ensureUserLoginAsync:i}});
;define("translation:widget/header/navigation/navigation",function(n,t){"use strict";function i(){r.on("mouseover",".account-wrap",function(){$(".account-panel").show()
}),r.on("mouseout",".account-wrap",function(){$(".account-panel").hide()}),$(".browser-edition-tip a").on("click",a),r.on("click",".account-login",o)
}function o(n){n.preventDefault(),c.newLoginInstanceAsync(),_hmt.push(["_trackEvent","首页","web端点击登录"])}function e(){(/msie 6\.0/i.test(navigator.userAgent)===!0||/msie 7\.0/i.test(navigator.userAgent)===!0)&&$(".browser-edition-tip").show()
}function a(){$(".browser-edition-tip").hide()}var c=n("translation:widget/login/login"),r=$(".header .navigation");t.init=function(){i(),e()
}});