// Copyright 2013 Active Inc. All rights reserved.
// $Id$
package com.wutai.tradingeq.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.wutai.tradingeq.domain.OID;

/**
 * @author mchen1
 * @versin $Rev$, $Date$
 * @since 1.0
 */
public class OIDTypeHandler extends BaseTypeHandler<OID> {

    @Override
    public OID getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return OID.fromString(rs.getString(columnName));
    }

    @Override
    public OID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return OID.fromString(rs.getString(columnIndex));
    }

    @Override
    public OID getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return OID.fromString(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, OID param,
            JdbcType jdbcType) throws SQLException {
        ps.setString(i, param.toString());
    }
}
