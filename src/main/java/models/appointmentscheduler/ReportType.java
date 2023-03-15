package models.appointmentscheduler;

public class ReportType {
        private String Month;
        private String Type;
        private int Count;

        public ReportType(String month, String type, int count){
            this.Month = month;
            this.Type = type;
            this.Count   = count;
        }

        public String getMonth() {
            return Month;
        }

        public void setMonth(String month) {
            Month = month;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public int getCount() {
            return Count;
        }

        public void setCount(int count) {
            Count = count;
        }
}
