package com.cly.sunyan.util;

import com.cly.sunyan.bean.article.ArticleNode;
import com.cly.sunyan.util.gson.GsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ListToArticleNodeTypeHandler extends BaseTypeHandler<List<ArticleNode>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<ArticleNode> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, GsonUtil.objectToJson(parameter));
    }

    @Override
    public List<ArticleNode> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        return GsonUtil.jsonToArray(string, ArticleNode.class);
    }

    @Override
    public List<ArticleNode> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        return GsonUtil.jsonToArray(string, ArticleNode.class);
    }

    @Override
    public List<ArticleNode> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        return GsonUtil.jsonToArray(string, ArticleNode.class);
    }
}