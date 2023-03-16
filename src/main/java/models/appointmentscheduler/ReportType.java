package models.appointmentscheduler;

/**
 * ReportType Model for Reports Appointments By Type & Month
 */

public class ReportType {
        private String Month;
        private String Type;
        private int Count;

        /**
         * Constructor
         * @param month
         * @param type
         * @param count
         */

        public ReportType(String month, String type, int count){
            this.Month = month;
            this.Type = type;
            this.Count   = count;
        }

        /**
         * Get Month
         * @return Month
         */

        public String getMonth() {
            return Month;
        }

        /**
         * Set Month
         * @param month
         */

        public void setMonth(String month) {
            Month = month;
        }

        /**
         * Get Type
         * @return Type
         */

        public String getType() {
            return Type;
        }

        /**
         * Set Type
         * @param type
         */

        public void setType(String type) {
            Type = type;
        }

        /**
         * Get Count
         * @return Count
         */

        public int getCount() {
            return Count;
        }

        /**
         * Set Count
         * @param count
         */

        public void setCount(int count) {
            Count = count;
        }
}
