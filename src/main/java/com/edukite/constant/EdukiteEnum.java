package com.edukite.constant;

/**
 * Created by dinhanhthai on 09/08/2020.
 */
public class EdukiteEnum {
    public enum BUS_STOP_STATUS {
        NOT_YET(1),
        ARRIVED(2),
        LEFT(3),
        SKIPPED(4);

        private final int status;
        public Integer getValue(){ return status;}
        BUS_STOP_STATUS(int status) {
            this.status = status;
        }
    }

    public enum SCHEDULE_STATUS {
        NOT_YET(1),
        ON_GOING(2),
        FINISHED(3),
        DELETED(4);

        private final int status;
        public Integer getValue(){ return status;}
        SCHEDULE_STATUS(int status) {
            this.status = status;
        }
    }


    public enum STUDENT_CHECK_STATUS {
        NOT_YET(1),
        CHECKED(2),
        ABSENT(3),
        EARLY_CHECKED(4);

        private final int status;

        STUDENT_CHECK_STATUS(int status) {
            this.status = status;
        }

        public Integer getValue(){ return status;}
    }

    public enum CHECK_TYPE {
        CHECKIN(1),
        CHECKOUT(2),
       ;
        private final int status;
        public Integer getValue(){ return status;}
        CHECK_TYPE(int status) {
            this.status = status;
        }
    }
}
