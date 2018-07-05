package com.xuan.xutils.time;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;

/**
 * 时间型不可变类的自定义序列化控制器
 * 保证类的单例,不可变性
 * @author kotomi
 *
 */
final class Ser implements Externalizable{
	
	static final byte LUNAR_CALENDAR_LOCAL_DATE=1;
	
	private byte type;
	private Object object;

	public Ser() { }
	
	public Ser(byte type, Object object) {
		this.type = type;
		this.object = object;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		writeInternal(this.type, this.object, out);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.type=in.readByte();
		this.object=readInternal(type, in);
	}
	
	static void writeInternal(byte type, Object object, ObjectOutput out) throws IOException{
		out.writeByte(type);
		switch (type) {
		case LUNAR_CALENDAR_LOCAL_DATE:
			((LunarCalendarLocalDate)object).writeExternal(out);
			break;
		default:
			throw new InvalidClassException("Unknown serialized type");
		}
	}
	
	static Object read(ObjectInput in) throws IOException{
		byte type=in.readByte();
		return readInternal(type, in);
	}
	
	private static Object readInternal(byte type, ObjectInput in) throws IOException{
		switch(type){
		case LUNAR_CALENDAR_LOCAL_DATE:return LunarCalendarLocalDate.readExternal(in);
		default:
			throw new StreamCorruptedException("Unknown serialized type");
		}
	}

	private Object readResolve() {
		return object;
	}
}
