package dictionnary;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Menu {

    Scanner input = new Scanner(System.in);

    ArrayList<Word> words = new ArrayList<>();

    // Phương thức thêm từ
    public void AddWord(String FilePath) throws IOException {
        FileWriter fw = new FileWriter(FilePath, true);
        BufferedWriter bw = new BufferedWriter(fw);

        FileReader fr = new FileReader(FilePath);
        BufferedReader br = new BufferedReader(fr);

        System.out.print("Nhap so luong chu ma ban muon them vao tu dien:");
        int num = input.nextInt();
        input.nextLine();
        for (int i = 0; i < num; i++) {

            Word w = new Word();

            System.out.print("Nhap tu tieng anh thu " + (i + 1) + " ma ban muon them nghia");
            w.setEn(input.nextLine());
            

            String str;
            int a=0;//tạo biến a,nếu từ đã có thì a = 1
            while((str = br.readLine()) != null){
                if(str.contains(w.getEn())){
                    System.out.println("Tu nay da ton tai roi!!");
                    a++;
                    break;
                }
            }
            
            if(a != 1)
            {
            System.out.print("Nhap nghia cua tu tieng anh ben tren");
            w.setVn(input.nextLine());

            bw.write(w.toString());
            bw.newLine();
            }
            
            
        }
        bw.close();
        fw.close();
        br.close();
        fr.close();
        
    }

    // Phương thức xóa từ
    public void DeleteWord(String FilePath) throws IOException {

        System.out.println("Nhap so luong tu ma ban muon xoa");
        int num = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= num; i++) {

            System.out.println("Nhap chu cai tieng Anh thu " + i + " ma ban muon xoa");
            String wantToDelete = input.nextLine();

            FileReader fr = new FileReader(FilePath);
            BufferedReader br = new BufferedReader(fr);

            // bw để ghi đè và thay thế file cũ còn bw1 để ghi nối tiếp vào file cũ
            // Ghi đè tất cả những dòng trong file cũ vào file mới trừ dòng có từ muốn xóa
            FileWriter fw = new FileWriter(FilePath);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Danh sach cac tu hien co : ");
            bw.newLine();

            FileWriter fw1 = new FileWriter(FilePath);
            BufferedWriter bw1 = new BufferedWriter(fw);

            String str;

            ArrayList<String> trungGian = new ArrayList<>();

            while ((str = br.readLine()) != null) {

                if (str.equalsIgnoreCase(wantToDelete)) {
                    continue;
                }

                bw1.write(str);
                bw1.newLine();
            }
            System.out.println("Da xoa thanh cong tu tren");

            bw.close();
            fw.close();
            bw1.close();
            fw1.close();
            br.close();
            fr.close();

        }
    }

    // Phương thức hiển thị danh sách từ
    public void displayWord(String FilePath) throws IOException {

        FileReader fr = new FileReader(FilePath);
        BufferedReader br = new BufferedReader(fr);

        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }

        br.close();
        fr.close();
    }

    // Phương thức tìm từ bằng cách nhập vào cả một từ hoặc nhập vào tiền tố
    public void findWordFull(String FilePath) throws IOException {

        System.out.println("Ban muon tim 1 tu hay tim theo tien to?");
        System.out.println("1:Tim theo tu        2:Tim theo tien to");
        System.out.print("Nhap lua chon cua ban :");
        int choose = input.nextInt();

        switch (choose) {
            case 1: {
                FileReader fr = new FileReader(FilePath);
                BufferedReader br = new BufferedReader(fr);

                System.out.print("Nhap tu ma ban muon tim");
                String wantToFind = input.nextLine();

                String str;
                while ((str = br.readLine()) != null) {
                    if (str.contains(wantToFind))
                        System.out.println(str);
                }
                br.close();
                fr.close();
                break;
            }
            case 2:{

                System.out.print("Nhap tien to cua tu ban muon tim");
                String tienTo = input.nextLine();

                FileReader fr = new FileReader(FilePath);
                BufferedReader br = new BufferedReader(fr);

                String str;
                while ((str = br.readLine()) != null) {

                    String arrString[] = str.split(".");
                    /*arrString tương đương với một mảng gồm 2 phần,1 phần là
                    tất cả những chữ cái còn lại
                    */
                    //arrString[0] là số thứ tự
                    //arrString[1] sẽ là chuỗi bắt đầu bằng chữ cái tiếng Anh
                    if(arrString[2].startsWith(tienTo)){
                        System.out.println(str);
                    }

                }

                br.close();
                fr.close();
                break;
            }
            default:{
                System.out.println("Chi nhap 1 hoac 2!!!");
            }
        }
    }

    //Phương thức dùng để sửa 1 từ
    public void changeMeaning(String FilePath) throws FileNotFoundException, IOException{
        System.out.print("Nhap tu tieng Anh ma ban muon sua: ");
        String wantToChange = input.nextLine();
        System.out.print("Nhap nghia dung cua tu tieng Anh do:");
        String changeTo = input.nextLine();
        
        String changeInFile = wantToChange + " : " + changeTo;
        
        FileReader fr = new FileReader(FilePath);
        BufferedReader br = new BufferedReader(fr);
        
        FileWriter fw1 = new FileWriter("FilePath");
        BufferedWriter bw1 = new BufferedWriter(fw1);
        
        FileWriter fw = new FileWriter("FilePat",true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw1.write("Danh sách từ hiện có :");
        
        String str;
        while((str = br.readLine()) != null){
            if(str.contains(wantToChange)){
                bw.write(changeInFile);
                bw.newLine();
            }
            bw.write(str);
            bw.newLine();
        }
        
        bw1.close();
        fw1.close();
        bw.close();
        fw.close();
        br.close();
        fr.close();
    }
    
    
    //Cái method này đang để thử kết nối với form
    public String FindWord(String FilePath) throws FileNotFoundException, IOException{
                LookUpWordForGuess lo = new LookUpWordForGuess();
                
                FileReader fr = new FileReader(FilePath);
                BufferedReader br = new BufferedReader(fr);

                
                String wantToFind = lo.getTaTuTiengAnh();

                String str;
                while ((str = br.readLine()) != null) {
                    if(str.contains(wantToFind)){
                        break;
                    }
                }
                
                br.close();
                fr.close();
                return str;    
    }
    
}
