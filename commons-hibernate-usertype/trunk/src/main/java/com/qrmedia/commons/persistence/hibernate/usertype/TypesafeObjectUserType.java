/*
 * @(#)PersitenceUserType.java     Oct 3, 2007
 *
 * Copyright © 2009 Andrew Phillips.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.qrmedia.commons.persistence.hibernate.usertype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import com.thoughtworks.xstream.XStream;

/**
 * A {@link UserType} that preserves the runtime type of the persisted object, even if the 
 * field's declared type is a supertype of the object being stored.
 * <p>
 * Unlike the default JPA object mapping, {@code TypesafeObjectUserType} can also be used 
 * for properties that do not implement {@link Serializable}. For restrictions on the types
 * of objects that can be handled by this user type see the <a href="http://xstream.codehaus.org">XStream documentation</a>.
 * 
 * @author rvd
 * @author anph
 * @since Oct 3, 2007
 * 
 */
public class TypesafeObjectUserType extends CollectionReturningUserType {
    // thread-safe, according to the XStream documentation
    private static final XStream XSTREAM = new XStream();
    
    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.usertype.UserType#returnedClass()
     */
    public Class<Object> returnedClass() {
        return Object.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.hibernate.usertype.UserType#sqlTypes()
     */
    public int[] sqlTypes() {
        return new int[] { Types.VARCHAR };
    }

    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], java.lang.Object)
     */
    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
            throws HibernateException, SQLException {
        return XSTREAM.fromXML((String) Hibernate.STRING.nullSafeGet(resultSet, names[0]));        
    }

    /* (non-Javadoc)
     * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int)
     */
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index)
            throws HibernateException, SQLException {
        Hibernate.STRING.nullSafeSet(preparedStatement, XSTREAM.toXML(value), index);
    }

    /* (non-Javadoc)
     * @see com.qrmedia.commons.persistence.hibernate.usertype.AbstractMutableCollectionReturningUserType#deepCopyValue(java.lang.Object)
     */
    @Override
    protected Object deepCopyValue(Object value) {
        // only collections are properly deep copied at present, and the superclass handles that 
        return value;
    }

}