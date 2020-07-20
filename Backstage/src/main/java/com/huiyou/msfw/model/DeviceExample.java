package com.huiyou.msfw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import me.fishlord.common.pageable.PageExample;

public class DeviceExample extends PageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceExample() {
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

    public DeviceExample(Integer pageNo, Integer pageSize) {
        super(pageNo, pageSize);
        oredCriteria = new ArrayList<Criteria>();
    }

    public DeviceExample(Integer pageNo, Integer pageSize, String orderByClause) {
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

        public Criteria andPssswordIsNull() {
            addCriterion("psssword is null");
            return (Criteria) this;
        }

        public Criteria andPssswordIsNotNull() {
            addCriterion("psssword is not null");
            return (Criteria) this;
        }

        public Criteria andPssswordEqualTo(String value) {
            addCriterion("psssword =", value, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordNotEqualTo(String value) {
            addCriterion("psssword <>", value, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordGreaterThan(String value) {
            addCriterion("psssword >", value, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordGreaterThanOrEqualTo(String value) {
            addCriterion("psssword >=", value, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordLessThan(String value) {
            addCriterion("psssword <", value, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordLessThanOrEqualTo(String value) {
            addCriterion("psssword <=", value, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordLike(String value) {
            addCriterion("psssword like", value, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordNotLike(String value) {
            addCriterion("psssword not like", value, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordIn(List<String> values) {
            addCriterion("psssword in", values, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordNotIn(List<String> values) {
            addCriterion("psssword not in", values, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordBetween(String value1, String value2) {
            addCriterion("psssword between", value1, value2, "psssword");
            return (Criteria) this;
        }

        public Criteria andPssswordNotBetween(String value1, String value2) {
            addCriterion("psssword not between", value1, value2, "psssword");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsIsNull() {
            addCriterion("login_seconds is null");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsIsNotNull() {
            addCriterion("login_seconds is not null");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsEqualTo(Integer value) {
            addCriterion("login_seconds =", value, "loginSeconds");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsNotEqualTo(Integer value) {
            addCriterion("login_seconds <>", value, "loginSeconds");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsGreaterThan(Integer value) {
            addCriterion("login_seconds >", value, "loginSeconds");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsGreaterThanOrEqualTo(Integer value) {
            addCriterion("login_seconds >=", value, "loginSeconds");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsLessThan(Integer value) {
            addCriterion("login_seconds <", value, "loginSeconds");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsLessThanOrEqualTo(Integer value) {
            addCriterion("login_seconds <=", value, "loginSeconds");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsIn(List<Integer> values) {
            addCriterion("login_seconds in", values, "loginSeconds");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsNotIn(List<Integer> values) {
            addCriterion("login_seconds not in", values, "loginSeconds");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsBetween(Integer value1, Integer value2) {
            addCriterion("login_seconds between", value1, value2, "loginSeconds");
            return (Criteria) this;
        }

        public Criteria andLoginSecondsNotBetween(Integer value1, Integer value2) {
            addCriterion("login_seconds not between", value1, value2, "loginSeconds");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIsNull() {
            addCriterion("login_time is null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIsNotNull() {
            addCriterion("login_time is not null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeEqualTo(Date value) {
            addCriterion("login_time =", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotEqualTo(Date value) {
            addCriterion("login_time <>", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeGreaterThan(Date value) {
            addCriterion("login_time >", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("login_time >=", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLessThan(Date value) {
            addCriterion("login_time <", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("login_time <=", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIn(List<Date> values) {
            addCriterion("login_time in", values, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotIn(List<Date> values) {
            addCriterion("login_time not in", values, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeBetween(Date value1, Date value2) {
            addCriterion("login_time between", value1, value2, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("login_time not between", value1, value2, "loginTime");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesIsNull() {
            addCriterion("experiment_times is null");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesIsNotNull() {
            addCriterion("experiment_times is not null");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesEqualTo(Integer value) {
            addCriterion("experiment_times =", value, "experimentTimes");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesNotEqualTo(Integer value) {
            addCriterion("experiment_times <>", value, "experimentTimes");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesGreaterThan(Integer value) {
            addCriterion("experiment_times >", value, "experimentTimes");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("experiment_times >=", value, "experimentTimes");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesLessThan(Integer value) {
            addCriterion("experiment_times <", value, "experimentTimes");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesLessThanOrEqualTo(Integer value) {
            addCriterion("experiment_times <=", value, "experimentTimes");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesIn(List<Integer> values) {
            addCriterion("experiment_times in", values, "experimentTimes");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesNotIn(List<Integer> values) {
            addCriterion("experiment_times not in", values, "experimentTimes");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesBetween(Integer value1, Integer value2) {
            addCriterion("experiment_times between", value1, value2, "experimentTimes");
            return (Criteria) this;
        }

        public Criteria andExperimentTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("experiment_times not between", value1, value2, "experimentTimes");
            return (Criteria) this;
        }

        public Criteria andGeneralLogIsNull() {
            addCriterion("general_log is null");
            return (Criteria) this;
        }

        public Criteria andGeneralLogIsNotNull() {
            addCriterion("general_log is not null");
            return (Criteria) this;
        }

        public Criteria andGeneralLogEqualTo(String value) {
            addCriterion("general_log =", value, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogNotEqualTo(String value) {
            addCriterion("general_log <>", value, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogGreaterThan(String value) {
            addCriterion("general_log >", value, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogGreaterThanOrEqualTo(String value) {
            addCriterion("general_log >=", value, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogLessThan(String value) {
            addCriterion("general_log <", value, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogLessThanOrEqualTo(String value) {
            addCriterion("general_log <=", value, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogLike(String value) {
            addCriterion("general_log like", value, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogNotLike(String value) {
            addCriterion("general_log not like", value, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogIn(List<String> values) {
            addCriterion("general_log in", values, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogNotIn(List<String> values) {
            addCriterion("general_log not in", values, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogBetween(String value1, String value2) {
            addCriterion("general_log between", value1, value2, "generalLog");
            return (Criteria) this;
        }

        public Criteria andGeneralLogNotBetween(String value1, String value2) {
            addCriterion("general_log not between", value1, value2, "generalLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogIsNull() {
            addCriterion("warning_log is null");
            return (Criteria) this;
        }

        public Criteria andWarningLogIsNotNull() {
            addCriterion("warning_log is not null");
            return (Criteria) this;
        }

        public Criteria andWarningLogEqualTo(String value) {
            addCriterion("warning_log =", value, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogNotEqualTo(String value) {
            addCriterion("warning_log <>", value, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogGreaterThan(String value) {
            addCriterion("warning_log >", value, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogGreaterThanOrEqualTo(String value) {
            addCriterion("warning_log >=", value, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogLessThan(String value) {
            addCriterion("warning_log <", value, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogLessThanOrEqualTo(String value) {
            addCriterion("warning_log <=", value, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogLike(String value) {
            addCriterion("warning_log like", value, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogNotLike(String value) {
            addCriterion("warning_log not like", value, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogIn(List<String> values) {
            addCriterion("warning_log in", values, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogNotIn(List<String> values) {
            addCriterion("warning_log not in", values, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogBetween(String value1, String value2) {
            addCriterion("warning_log between", value1, value2, "warningLog");
            return (Criteria) this;
        }

        public Criteria andWarningLogNotBetween(String value1, String value2) {
            addCriterion("warning_log not between", value1, value2, "warningLog");
            return (Criteria) this;
        }

        public Criteria andServiceLifeIsNull() {
            addCriterion("service_life is null");
            return (Criteria) this;
        }

        public Criteria andServiceLifeIsNotNull() {
            addCriterion("service_life is not null");
            return (Criteria) this;
        }

        public Criteria andServiceLifeEqualTo(Integer value) {
            addCriterion("service_life =", value, "serviceLife");
            return (Criteria) this;
        }

        public Criteria andServiceLifeNotEqualTo(Integer value) {
            addCriterion("service_life <>", value, "serviceLife");
            return (Criteria) this;
        }

        public Criteria andServiceLifeGreaterThan(Integer value) {
            addCriterion("service_life >", value, "serviceLife");
            return (Criteria) this;
        }

        public Criteria andServiceLifeGreaterThanOrEqualTo(Integer value) {
            addCriterion("service_life >=", value, "serviceLife");
            return (Criteria) this;
        }

        public Criteria andServiceLifeLessThan(Integer value) {
            addCriterion("service_life <", value, "serviceLife");
            return (Criteria) this;
        }

        public Criteria andServiceLifeLessThanOrEqualTo(Integer value) {
            addCriterion("service_life <=", value, "serviceLife");
            return (Criteria) this;
        }

        public Criteria andServiceLifeIn(List<Integer> values) {
            addCriterion("service_life in", values, "serviceLife");
            return (Criteria) this;
        }

        public Criteria andServiceLifeNotIn(List<Integer> values) {
            addCriterion("service_life not in", values, "serviceLife");
            return (Criteria) this;
        }

        public Criteria andServiceLifeBetween(Integer value1, Integer value2) {
            addCriterion("service_life between", value1, value2, "serviceLife");
            return (Criteria) this;
        }

        public Criteria andServiceLifeNotBetween(Integer value1, Integer value2) {
            addCriterion("service_life not between", value1, value2, "serviceLife");
            return (Criteria) this;
        }

        public Criteria andFilenameIsNull() {
            addCriterion("fileName is null");
            return (Criteria) this;
        }

        public Criteria andFilenameIsNotNull() {
            addCriterion("fileName is not null");
            return (Criteria) this;
        }

        public Criteria andFilenameEqualTo(String value) {
            addCriterion("fileName =", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotEqualTo(String value) {
            addCriterion("fileName <>", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameGreaterThan(String value) {
            addCriterion("fileName >", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameGreaterThanOrEqualTo(String value) {
            addCriterion("fileName >=", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLessThan(String value) {
            addCriterion("fileName <", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLessThanOrEqualTo(String value) {
            addCriterion("fileName <=", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLike(String value) {
            addCriterion("fileName like", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotLike(String value) {
            addCriterion("fileName not like", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameIn(List<String> values) {
            addCriterion("fileName in", values, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotIn(List<String> values) {
            addCriterion("fileName not in", values, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameBetween(String value1, String value2) {
            addCriterion("fileName between", value1, value2, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotBetween(String value1, String value2) {
            addCriterion("fileName not between", value1, value2, "filename");
            return (Criteria) this;
        }

        public Criteria andFileIsNull() {
            addCriterion("file is null");
            return (Criteria) this;
        }

        public Criteria andFileIsNotNull() {
            addCriterion("file is not null");
            return (Criteria) this;
        }

        public Criteria andFileEqualTo(String value) {
            addCriterion("file =", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotEqualTo(String value) {
            addCriterion("file <>", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileGreaterThan(String value) {
            addCriterion("file >", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileGreaterThanOrEqualTo(String value) {
            addCriterion("file >=", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileLessThan(String value) {
            addCriterion("file <", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileLessThanOrEqualTo(String value) {
            addCriterion("file <=", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileLike(String value) {
            addCriterion("file like", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotLike(String value) {
            addCriterion("file not like", value, "file");
            return (Criteria) this;
        }

        public Criteria andFileIn(List<String> values) {
            addCriterion("file in", values, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotIn(List<String> values) {
            addCriterion("file not in", values, "file");
            return (Criteria) this;
        }

        public Criteria andFileBetween(String value1, String value2) {
            addCriterion("file between", value1, value2, "file");
            return (Criteria) this;
        }

        public Criteria andFileNotBetween(String value1, String value2) {
            addCriterion("file not between", value1, value2, "file");
            return (Criteria) this;
        }

        public Criteria andServiceStatusIsNull() {
            addCriterion("service_status is null");
            return (Criteria) this;
        }

        public Criteria andServiceStatusIsNotNull() {
            addCriterion("service_status is not null");
            return (Criteria) this;
        }

        public Criteria andServiceStatusEqualTo(Integer value) {
            addCriterion("service_status =", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusNotEqualTo(Integer value) {
            addCriterion("service_status <>", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusGreaterThan(Integer value) {
            addCriterion("service_status >", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("service_status >=", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusLessThan(Integer value) {
            addCriterion("service_status <", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("service_status <=", value, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusIn(List<Integer> values) {
            addCriterion("service_status in", values, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusNotIn(List<Integer> values) {
            addCriterion("service_status not in", values, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusBetween(Integer value1, Integer value2) {
            addCriterion("service_status between", value1, value2, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andServiceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("service_status not between", value1, value2, "serviceStatus");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Double value) {
            addCriterion("size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Double value) {
            addCriterion("size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Double value) {
            addCriterion("size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Double value) {
            addCriterion("size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Double value) {
            addCriterion("size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Double value) {
            addCriterion("size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Double> values) {
            addCriterion("size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Double> values) {
            addCriterion("size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Double value1, Double value2) {
            addCriterion("size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Double value1, Double value2) {
            addCriterion("size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andUserSizeIsNull() {
            addCriterion("user_size is null");
            return (Criteria) this;
        }

        public Criteria andUserSizeIsNotNull() {
            addCriterion("user_size is not null");
            return (Criteria) this;
        }

        public Criteria andUserSizeEqualTo(Double value) {
            addCriterion("user_size =", value, "userSize");
            return (Criteria) this;
        }

        public Criteria andUserSizeNotEqualTo(Double value) {
            addCriterion("user_size <>", value, "userSize");
            return (Criteria) this;
        }

        public Criteria andUserSizeGreaterThan(Double value) {
            addCriterion("user_size >", value, "userSize");
            return (Criteria) this;
        }

        public Criteria andUserSizeGreaterThanOrEqualTo(Double value) {
            addCriterion("user_size >=", value, "userSize");
            return (Criteria) this;
        }

        public Criteria andUserSizeLessThan(Double value) {
            addCriterion("user_size <", value, "userSize");
            return (Criteria) this;
        }

        public Criteria andUserSizeLessThanOrEqualTo(Double value) {
            addCriterion("user_size <=", value, "userSize");
            return (Criteria) this;
        }

        public Criteria andUserSizeIn(List<Double> values) {
            addCriterion("user_size in", values, "userSize");
            return (Criteria) this;
        }

        public Criteria andUserSizeNotIn(List<Double> values) {
            addCriterion("user_size not in", values, "userSize");
            return (Criteria) this;
        }

        public Criteria andUserSizeBetween(Double value1, Double value2) {
            addCriterion("user_size between", value1, value2, "userSize");
            return (Criteria) this;
        }

        public Criteria andUserSizeNotBetween(Double value1, Double value2) {
            addCriterion("user_size not between", value1, value2, "userSize");
            return (Criteria) this;
        }

        public Criteria andDayhaveIsNull() {
            addCriterion("dayhave is null");
            return (Criteria) this;
        }

        public Criteria andDayhaveIsNotNull() {
            addCriterion("dayhave is not null");
            return (Criteria) this;
        }

        public Criteria andDayhaveEqualTo(Integer value) {
            addCriterion("dayhave =", value, "dayhave");
            return (Criteria) this;
        }

        public Criteria andDayhaveNotEqualTo(Integer value) {
            addCriterion("dayhave <>", value, "dayhave");
            return (Criteria) this;
        }

        public Criteria andDayhaveGreaterThan(Integer value) {
            addCriterion("dayhave >", value, "dayhave");
            return (Criteria) this;
        }

        public Criteria andDayhaveGreaterThanOrEqualTo(Integer value) {
            addCriterion("dayhave >=", value, "dayhave");
            return (Criteria) this;
        }

        public Criteria andDayhaveLessThan(Integer value) {
            addCriterion("dayhave <", value, "dayhave");
            return (Criteria) this;
        }

        public Criteria andDayhaveLessThanOrEqualTo(Integer value) {
            addCriterion("dayhave <=", value, "dayhave");
            return (Criteria) this;
        }

        public Criteria andDayhaveIn(List<Integer> values) {
            addCriterion("dayhave in", values, "dayhave");
            return (Criteria) this;
        }

        public Criteria andDayhaveNotIn(List<Integer> values) {
            addCriterion("dayhave not in", values, "dayhave");
            return (Criteria) this;
        }

        public Criteria andDayhaveBetween(Integer value1, Integer value2) {
            addCriterion("dayhave between", value1, value2, "dayhave");
            return (Criteria) this;
        }

        public Criteria andDayhaveNotBetween(Integer value1, Integer value2) {
            addCriterion("dayhave not between", value1, value2, "dayhave");
            return (Criteria) this;
        }

        public Criteria andIsdongjieIsNull() {
            addCriterion("isdongjie is null");
            return (Criteria) this;
        }

        public Criteria andIsdongjieIsNotNull() {
            addCriterion("isdongjie is not null");
            return (Criteria) this;
        }

        public Criteria andIsdongjieEqualTo(Integer value) {
            addCriterion("isdongjie =", value, "isdongjie");
            return (Criteria) this;
        }

        public Criteria andIsdongjieNotEqualTo(Integer value) {
            addCriterion("isdongjie <>", value, "isdongjie");
            return (Criteria) this;
        }

        public Criteria andIsdongjieGreaterThan(Integer value) {
            addCriterion("isdongjie >", value, "isdongjie");
            return (Criteria) this;
        }

        public Criteria andIsdongjieGreaterThanOrEqualTo(Integer value) {
            addCriterion("isdongjie >=", value, "isdongjie");
            return (Criteria) this;
        }

        public Criteria andIsdongjieLessThan(Integer value) {
            addCriterion("isdongjie <", value, "isdongjie");
            return (Criteria) this;
        }

        public Criteria andIsdongjieLessThanOrEqualTo(Integer value) {
            addCriterion("isdongjie <=", value, "isdongjie");
            return (Criteria) this;
        }

        public Criteria andIsdongjieIn(List<Integer> values) {
            addCriterion("isdongjie in", values, "isdongjie");
            return (Criteria) this;
        }

        public Criteria andIsdongjieNotIn(List<Integer> values) {
            addCriterion("isdongjie not in", values, "isdongjie");
            return (Criteria) this;
        }

        public Criteria andIsdongjieBetween(Integer value1, Integer value2) {
            addCriterion("isdongjie between", value1, value2, "isdongjie");
            return (Criteria) this;
        }

        public Criteria andIsdongjieNotBetween(Integer value1, Integer value2) {
            addCriterion("isdongjie not between", value1, value2, "isdongjie");
            return (Criteria) this;
        }

        public Criteria andDongdaysIsNull() {
            addCriterion("dongdays is null");
            return (Criteria) this;
        }

        public Criteria andDongdaysIsNotNull() {
            addCriterion("dongdays is not null");
            return (Criteria) this;
        }

        public Criteria andDongdaysEqualTo(Integer value) {
            addCriterion("dongdays =", value, "dongdays");
            return (Criteria) this;
        }

        public Criteria andDongdaysNotEqualTo(Integer value) {
            addCriterion("dongdays <>", value, "dongdays");
            return (Criteria) this;
        }

        public Criteria andDongdaysGreaterThan(Integer value) {
            addCriterion("dongdays >", value, "dongdays");
            return (Criteria) this;
        }

        public Criteria andDongdaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("dongdays >=", value, "dongdays");
            return (Criteria) this;
        }

        public Criteria andDongdaysLessThan(Integer value) {
            addCriterion("dongdays <", value, "dongdays");
            return (Criteria) this;
        }

        public Criteria andDongdaysLessThanOrEqualTo(Integer value) {
            addCriterion("dongdays <=", value, "dongdays");
            return (Criteria) this;
        }

        public Criteria andDongdaysIn(List<Integer> values) {
            addCriterion("dongdays in", values, "dongdays");
            return (Criteria) this;
        }

        public Criteria andDongdaysNotIn(List<Integer> values) {
            addCriterion("dongdays not in", values, "dongdays");
            return (Criteria) this;
        }

        public Criteria andDongdaysBetween(Integer value1, Integer value2) {
            addCriterion("dongdays between", value1, value2, "dongdays");
            return (Criteria) this;
        }

        public Criteria andDongdaysNotBetween(Integer value1, Integer value2) {
            addCriterion("dongdays not between", value1, value2, "dongdays");
            return (Criteria) this;
        }

        public Criteria andDongtimeIsNull() {
            addCriterion("dongtime is null");
            return (Criteria) this;
        }

        public Criteria andDongtimeIsNotNull() {
            addCriterion("dongtime is not null");
            return (Criteria) this;
        }

        public Criteria andDongtimeEqualTo(Date value) {
            addCriterion("dongtime =", value, "dongtime");
            return (Criteria) this;
        }

        public Criteria andDongtimeNotEqualTo(Date value) {
            addCriterion("dongtime <>", value, "dongtime");
            return (Criteria) this;
        }

        public Criteria andDongtimeGreaterThan(Date value) {
            addCriterion("dongtime >", value, "dongtime");
            return (Criteria) this;
        }

        public Criteria andDongtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("dongtime >=", value, "dongtime");
            return (Criteria) this;
        }

        public Criteria andDongtimeLessThan(Date value) {
            addCriterion("dongtime <", value, "dongtime");
            return (Criteria) this;
        }

        public Criteria andDongtimeLessThanOrEqualTo(Date value) {
            addCriterion("dongtime <=", value, "dongtime");
            return (Criteria) this;
        }

        public Criteria andDongtimeIn(List<Date> values) {
            addCriterion("dongtime in", values, "dongtime");
            return (Criteria) this;
        }

        public Criteria andDongtimeNotIn(List<Date> values) {
            addCriterion("dongtime not in", values, "dongtime");
            return (Criteria) this;
        }

        public Criteria andDongtimeBetween(Date value1, Date value2) {
            addCriterion("dongtime between", value1, value2, "dongtime");
            return (Criteria) this;
        }

        public Criteria andDongtimeNotBetween(Date value1, Date value2) {
            addCriterion("dongtime not between", value1, value2, "dongtime");
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