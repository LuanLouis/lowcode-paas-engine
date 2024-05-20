package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName lc_page
 */
@TableName(value ="lc_page")
@Data
public class LcPage implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 应用code
     */
    private String pageCode;

    /**
     * 应用名称
     */
    private String pageName;

    /**
     * 英文
     */
    private String pageNameEn;

    /**
     * 归属应用code
     */
    private String appCode;

    /**
     * 备注
     */
    private String appRemark;

    /**
     * 页面动态schema
     */
    private String pageSchema;

    /**
     * 依赖资源管理
     */
    private String pageAssets;

    /**
     * 逻辑删除 y，n
     */
    private String isDeleted;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近更新时间
     */
    private Date lastUpdateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LcPage other = (LcPage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPageCode() == null ? other.getPageCode() == null : this.getPageCode().equals(other.getPageCode()))
            && (this.getPageName() == null ? other.getPageName() == null : this.getPageName().equals(other.getPageName()))
            && (this.getPageNameEn() == null ? other.getPageNameEn() == null : this.getPageNameEn().equals(other.getPageNameEn()))
            && (this.getAppCode() == null ? other.getAppCode() == null : this.getAppCode().equals(other.getAppCode()))
            && (this.getAppRemark() == null ? other.getAppRemark() == null : this.getAppRemark().equals(other.getAppRemark()))
            && (this.getPageSchema() == null ? other.getPageSchema() == null : this.getPageSchema().equals(other.getPageSchema()))
            && (this.getPageAssets() == null ? other.getPageAssets() == null : this.getPageAssets().equals(other.getPageAssets()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPageCode() == null) ? 0 : getPageCode().hashCode());
        result = prime * result + ((getPageName() == null) ? 0 : getPageName().hashCode());
        result = prime * result + ((getPageNameEn() == null) ? 0 : getPageNameEn().hashCode());
        result = prime * result + ((getAppCode() == null) ? 0 : getAppCode().hashCode());
        result = prime * result + ((getAppRemark() == null) ? 0 : getAppRemark().hashCode());
        result = prime * result + ((getPageSchema() == null) ? 0 : getPageSchema().hashCode());
        result = prime * result + ((getPageAssets() == null) ? 0 : getPageAssets().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pageCode=").append(pageCode);
        sb.append(", pageName=").append(pageName);
        sb.append(", pageNameEn=").append(pageNameEn);
        sb.append(", appCode=").append(appCode);
        sb.append(", appRemark=").append(appRemark);
        sb.append(", pageSchema=").append(pageSchema);
        sb.append(", pageAssets=").append(pageAssets);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", creator=").append(creator);
        sb.append(", modifier=").append(modifier);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}