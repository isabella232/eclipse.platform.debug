/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.debug.core.model;


/**
 * A byte of memory in a meomry block. Each byte of memory has a value and
 * attributes indicating if the byte is read-only, valid, or if its value has
 * changed.
 * <p>
 * Clients may instantiate this class. Clients may subclass this class to 
 * add other attributes to a memory byte, as required.
 * </p>
 * @since 3.1
 * @see org.eclipse.debug.core.model.IMemoryBlockExtension
 */
public class MemoryByte {
	
    /**
     * Bit mask used to indicate a byte is read-only.
     */
	public static final byte	READONLY	= 0x01;
	
	/**
	 * Bit mask used to indicate a byte is valid.
	 * A memory byte is valid when its value and attributes are retrievable.
	 * Otherwise, a byte is considered invalid.
	 */
	public static final byte	VALID		= 0x02;
	
	/**
	 * Bit mask used to indicate a byte has changed since the last
	 * suspend event.
	 * 
	 * @see org.eclipse.debug.core.DebugEvent#SUSPEND
	 */
	public static final byte	CHANGED		= 0x04;
	
	/**
	 * Bit mask used to indicate a memory byte has history to
	 * determine if its value has changed. When a memory byte's
	 * history is unknown, the change state has no meaning.
	 */
	public static final byte 	KNOWN		= 0x08;
	
	/**
	 * Bit mask used to indicate a this byte of memory
	 * is big endian.  If this byte of memory is little endian,
	 * turn this bit mask to off.
	 */
	public static final byte	BIG_ENDIAN	= 0x10;
	
	/**
	 * Bit mask used to indicate that the endianess of this byte
	 * of memory is known.  When a memory byte's endianess is
	 * unknown, the endianess of this byte has no meaning.  
	 */
	public static final byte	ENDIANESS_KNOWN = 0x20;
	
	/**
	 * Value of this byte.
	 */
	protected byte value;
	
	/**
	 * Attribute flags.
	 * <p>
	 * To specify VALID:  flags |= MemoryByte.VALID;
	 * To specify READONLY:  flags |= MemoryByte.READONLY;
	 * </p>
	 */
	protected byte flags;
	
	/**
	 * Constructs a read-write, valid memory byte without a change history,
	 * and a value of 0.  The byte's endianess is known and is little endian
	 * by default.
	 */
	public MemoryByte() {
	    this((byte)0, (byte)(VALID | ENDIANESS_KNOWN));
	}
	
	/**
	 * Constructs a read-write, valid memory byte without a change history,
	 * with the given value.  The byte's endianess is known and is little endian
	 * by default.  
	 * 
	 * @param byteValue value of this memory byte
	 * 
	 */
	public MemoryByte(byte byteValue) {
	    this(byteValue, (byte)(VALID | ENDIANESS_KNOWN));
	}
	
	/**
	 * Constructs a memory byte with the given value and attributes.
	 * 
	 * @param byteValue value of this memory byte
	 * @param byteFlags attributes of the byte specified as a bit mask 
	 */
	public MemoryByte(byte byteValue, byte byteFlags) {		
		value = byteValue;
		flags = byteFlags;
	}

	/**
	 * Returns this memory byte's attribute as a bit mask.
	 * 
	 * @return this memory byte's attribute as a bit mask
	 */
	public byte getFlags() {
		return flags;
	}
	/**
	 * Sets this memory byte's attributes based on the given bit mask.
	 * 
	 * @param flags bit mask of attributes
	 */
	public void setFlags(byte flags) {
		this.flags = flags;
	}

	/**
	 * Returns the value of this memory byte.
	 * 
	 * @return the value of this memory byte
	 */
	public byte getValue() {
		return value;
	}
	
	/**
	 * Sets the value of this memory byte.
	 * 
	 * @param value the new value of this memory byte
	 */
	public void setValue(byte value) {
		this.value = value;
	}
	
	/**
	 * Sets whether this memory byte is valid. A memory byte
	 * is considered valid when its value and attributes are
	 * retrievable.
	 * 
	 * @param valid whether this memory byte is valid
	 */
	public void setValid(boolean valid) {
		flags |= MemoryByte.VALID;
		if (!valid)
			flags ^= MemoryByte.VALID;
	}
	
	/**
	 * Returns whether this memory byte is valid. A memory byte
	 * is considered valid when its value and attributes are
	 * retrievable.
	 * 
	 * @return whether this memory byte is valid
	 */
	public boolean isValid() {
		return ((flags & MemoryByte.VALID) == MemoryByte.VALID);
	}
	
	/**
	 * Sets whether this memory byte is read-only.
	 * 
	 * @param readonly whether this memory byte is read-only.
	 */
	public void setReadonly(boolean readonly) {
		flags |= MemoryByte.READONLY;
		if (!readonly)
			flags ^= MemoryByte.READONLY;
	}
	
	/**
	 * Returns whether this memory byte is read-only.
	 * 
	 * @return whether this memory byte is read-only
	 */
	public boolean isReadonly() {
		return ((flags & MemoryByte.READONLY) == MemoryByte.READONLY);
	}
	
	/**
	 * Sets whether this memory byte has changed.
	 * 
	 * @param changed whether this memory byte has changed
	 */
	public void setChanged(boolean changed) {
		flags |= MemoryByte.CHANGED;
		if (!changed)
			flags ^= MemoryByte.CHANGED;
	}
	
	/**
	 * Returns whether this memory byte has changed.
	 * 
	 * @return whether this memory byte has changed
	 */
	public boolean isChanged() {
		return ((flags & MemoryByte.CHANGED) == MemoryByte.CHANGED);
	}
	
	/**
	 * Sets whether the history of this byte is known. When history
	 * is unknown, the change state of a memory byte has no meaning.
	 * 
	 * @param known whether the change state of this byte is known
	 */
	public void setKnown(boolean known) {
		flags |= MemoryByte.KNOWN;
		if (!known)
			flags ^= MemoryByte.KNOWN;
	}
	
	/**
	 * Returns whether the history of this byte is known. When history
	 * is unknown, the change state of a memory byte has no meaning.
	 * 
	 * @return whether the change state of this byte is known
	 */
	public boolean isKnown() {
		return ((flags & MemoryByte.KNOWN) == MemoryByte.KNOWN);
	}
	
	/**
	 * Sets whether this byte of memory is big endian.
	 * 
	 * @param isBigEndian whether the byte of memory is big endian.
	 */
	public void setBigEndian(boolean isBigEndian)
	{
		flags |= MemoryByte.BIG_ENDIAN;
		if (!isBigEndian)
			flags ^= MemoryByte.BIG_ENDIAN;
	}
	
	/**
	 * Returns whether this byte of memory is big endian.
	 * 
	 * @return whether the byte of memory is big endian.
	 */
	public boolean isBigEndian()
	{
		return ((flags & MemoryByte.BIG_ENDIAN) == MemoryByte.BIG_ENDIAN);
	}
	
	/**
	 * Sets whether the endianess of this byte of memory is known.
	 * If the endianess is unknown, the endianess of this byte
	 * has no meaning. 
	 * 
	 * @param isEndianessKnown whether the endianess of this byte is known.
	 */
	public void setEndianessKnown(boolean isEndianessKnown)
	{
		flags |= MemoryByte.ENDIANESS_KNOWN;
		if (!isEndianessKnown)
			flags ^= MemoryByte.ENDIANESS_KNOWN;
	}
	
	/**
	 * Returns whether the endianess of this byte of memory is known.
	 * If the endianess is unknown, the endianess of this byte
	 * has no meaning.
	 *  
	 * @return whether the endianess of this byte of memory is known.
	 */
	public boolean isEndianessKnown()
	{
		return ((flags & MemoryByte.ENDIANESS_KNOWN) == MemoryByte.ENDIANESS_KNOWN);
	}
	
}
