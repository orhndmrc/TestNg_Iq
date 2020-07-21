package excelDriven;

public class ExcelOperations {
    public static void main(String[] args) {
        Xls_Reader reader = new Xls_Reader("C:\\Users\\demir\\TestNg_Iq\\ebay.xlsx");
        String firstname = reader.getCellData("ebayTest","firstname",2);
        System.out.println(firstname);
        String lastname = reader.getCellData("ebayTest","lastname",2);
        System.out.println(lastname);
        String email = reader.getCellData("ebayTest","email",2);
        System.out.println(email);
        String password = reader.getCellData("ebayTest","password",2);
        System.out.println(password);
    }
}
