package com.huiyou.msfw.model;

import java.util.ArrayList;
import java.util.List;
import me.fishlord.common.pageable.PageExample;

public class FolderExample extends PageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FolderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public FolderExample(Integer pageNo, Integer pageSize) {
        super(pageNo, pageSize);
        oredCriteria = new ArrayList<Criteria>();
    }

    public FolderExample(Integer pageNo, Integer pageSize, String orderByClause) {
        super(pageNo, pageSize);
        this.orderByClause = orderByClause;
        oredCriteria = new ArrayList<Criteria>();
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFolderIsNull() {
            addCriterion("folder is null");
            return (Criteria) this;
        }

        public Criteria andFolderIsNotNull() {
            addCriterion("folder is not null");
            return (Criteria) this;
        }

        public Criteria andFolderEqualTo(String value) {
            addCriterion("folder =", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderNotEqualTo(String value) {
            addCriterion("folder <>", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderGreaterThan(String value) {
            addCriterion("folder >", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderGreaterThanOrEqualTo(String value) {
            addCriterion("folder >=", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderLessThan(String value) {
            addCriterion("folder <", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderLessThanOrEqualTo(String value) {
            addCriterion("folder <=", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderLike(String value) {
            addCriterion("folder like", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderNotLike(String value) {
            addCriterion("folder not like", value, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderIn(List<String> values) {
            addCriterion("folder in", values, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderNotIn(List<String> values) {
            addCriterion("folder not in", values, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderBetween(String value1, String value2) {
            addCriterion("folder between", value1, value2, "folder");
            return (Criteria) this;
        }

        public Criteria andFolderNotBetween(String value1, String value2) {
            addCriterion("folder not between", value1, value2, "folder");
            return (Criteria) this;
        }

        public Criteria andChildFolderIsNull() {
            addCriterion("child_folder is null");
            return (Criteria) this;
        }

        public Criteria andChildFolderIsNotNull() {
            addCriterion("child_folder is not null");
            return (Criteria) this;
        }

        public Criteria andChildFolderEqualTo(String value) {
            addCriterion("child_folder =", value, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderNotEqualTo(String value) {
            addCriterion("child_folder <>", value, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderGreaterThan(String value) {
            addCriterion("child_folder >", value, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderGreaterThanOrEqualTo(String value) {
            addCriterion("child_folder >=", value, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderLessThan(String value) {
            addCriterion("child_folder <", value, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderLessThanOrEqualTo(String value) {
            addCriterion("child_folder <=", value, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderLike(String value) {
            addCriterion("child_folder like", value, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderNotLike(String value) {
            addCriterion("child_folder not like", value, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderIn(List<String> values) {
            addCriterion("child_folder in", values, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderNotIn(List<String> values) {
            addCriterion("child_folder not in", values, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderBetween(String value1, String value2) {
            addCriterion("child_folder between", value1, value2, "childFolder");
            return (Criteria) this;
        }

        public Criteria andChildFolderNotBetween(String value1, String value2) {
            addCriterion("child_folder not between", value1, value2, "childFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderIsNull() {
            addCriterion("is_folder is null");
            return (Criteria) this;
        }

        public Criteria andIsFolderIsNotNull() {
            addCriterion("is_folder is not null");
            return (Criteria) this;
        }

        public Criteria andIsFolderEqualTo(Integer value) {
            addCriterion("is_folder =", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderNotEqualTo(Integer value) {
            addCriterion("is_folder <>", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderGreaterThan(Integer value) {
            addCriterion("is_folder >", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_folder >=", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderLessThan(Integer value) {
            addCriterion("is_folder <", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderLessThanOrEqualTo(Integer value) {
            addCriterion("is_folder <=", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderIn(List<Integer> values) {
            addCriterion("is_folder in", values, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderNotIn(List<Integer> values) {
            addCriterion("is_folder not in", values, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderBetween(Integer value1, Integer value2) {
            addCriterion("is_folder between", value1, value2, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderNotBetween(Integer value1, Integer value2) {
            addCriterion("is_folder not between", value1, value2, "isFolder");
            return (Criteria) this;
        }

        public Criteria andSnIsNull() {
            addCriterion("sn is null");
            return (Criteria) this;
        }

        public Criteria andSnIsNotNull() {
            addCriterion("sn is not null");
            return (Criteria) this;
        }

        public Criteria andSnEqualTo(String value) {
            addCriterion("sn =", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotEqualTo(String value) {
            addCriterion("sn <>", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThan(String value) {
            addCriterion("sn >", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnGreaterThanOrEqualTo(String value) {
            addCriterion("sn >=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThan(String value) {
            addCriterion("sn <", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLessThanOrEqualTo(String value) {
            addCriterion("sn <=", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnLike(String value) {
            addCriterion("sn like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotLike(String value) {
            addCriterion("sn not like", value, "sn");
            return (Criteria) this;
        }

        public Criteria andSnIn(List<String> values) {
            addCriterion("sn in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotIn(List<String> values) {
            addCriterion("sn not in", values, "sn");
            return (Criteria) this;
        }

        public Criteria andSnBetween(String value1, String value2) {
            addCriterion("sn between", value1, value2, "sn");
            return (Criteria) this;
        }

        public Criteria andSnNotBetween(String value1, String value2) {
            addCriterion("sn not between", value1, value2, "sn");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}