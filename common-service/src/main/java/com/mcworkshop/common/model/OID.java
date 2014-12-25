// Copyright 2014 MCWorkshop Inc. All rights reserved.
// $Id$
package com.mcworkshop.common.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author $Author$
 * 
 */
public class OID implements Serializable {
    private static final long serialVersionUID = -7312952382513544192L;

    private UUID              value;

    private transient String  valueString;

    public final static int   PRINT_LENGTH     = 36;

    /**
     * Construct from UUID value.
     */
    public OID(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("Value should not be NULL.");
        } else {
            this.value = value;
        }
    }

    /**
     * Construct from string value. The value must match UUID format.
     */
    public OID(String value) {
        this.value = UUID.fromString(value);
    }

    /**
     * Create an instance from string value. The value must match UUID format.
     * 
     * @param String
     *            representation of UUID value.
     * @return OID instance.
     */
    public static OID fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value should not be NULL.");
        } else {
            return new OID(value);
        }
    }

    /**
     * Create an instance from string value. The value must match UUID format.
     * If value is NULL, this method will return NULL. This method fits most
     * needs of "create/update" API.
     * 
     * @param String
     *            representation of UUID value.
     * @return OID instance.
     */
    public static OID fromStringNullable(String value) {
        if (value == null) {
            return null;
        } else {
            return fromString(value);
        }
    }

    /**
     * Create an instance from string representation of a named byte array.
     * 
     * @param String
     *            representation of a named byte array.
     * @return OID instance.
     */
    public static OID nameFromString(String value) {
        return new OID(UUID.nameUUIDFromBytes(value.getBytes()));
    }

    /**
     * Create an instance with random UUID value.
     * 
     * @return OID instance.
     */
    public static OID generate() {
        return new OID(UUID.randomUUID());
    }

    public UUID getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        if (this.valueString == null) {
            this.valueString = this.value.toString().toUpperCase();
        }
        return this.valueString;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object value) {
        if (this == value) {
            return true;
        }
        if (value == null) {
            return false;
        }
        if (value instanceof OID) {
            OID another = (OID) value;
            return this.value.equals(another.getValue());
        } else {
            return false;
        }
    }
}
