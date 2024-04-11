package vehiclemanagement;

class Vehicle {
    
    private String regNo;
    private String make;
    private int YearOfManufacture;
    private double value;
    
        public Vehicle (String regNo, String make, int YearOfManufacture, double value) {
            this.regNo = regNo;
            this.make = make;
            this.YearOfManufacture = YearOfManufacture;
            this.value = value;
        }
        
        public int calculateAge(int currentYear) {
            return currentYear - YearOfManufacture;
        }
        
        public int getYearOfManufacture() {
            return YearOfManufacture;
        }
        
        public void setYearOfManufacture(int YearOfManufacture) {
            this.YearOfManufacture = YearOfManufacture;
        }
        
        public double getvalue() {
            return value;
        }
        
        public void setvalue(double value) {
            this.value = value;
        }
        
        @Override
        public String toString() {
            return ("Reg No: " + regNo + ", Make: " + make + ", Year of Manufacture: " 
                    + YearOfManufacture + ", Value: Php" + value); 
        }

    Object getRegNo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}   
