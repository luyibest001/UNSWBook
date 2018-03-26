package unswbook;





import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class search extends initialdb{
    public List<profile> impsearch(String name, String dob, String gender){
        List re=new ArrayList<profile>();
        List ans=new ArrayList<profile>();
        int idob;
        try {
			startdb();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        String username=null;
        int flag=0;
        String se="SELECT * FROM profile WHERE ";

        if (!name.equals("#")) {
            se = se + " name LIKE '%" + name + "%' ";
            flag=1;


        }

        if(!gender.equals("#")){
            if (flag==1){
                se = se + " AND ";
            }
            se = se + " gender LIKE '" + gender + "%' ";
            flag=1;
        }
        if (flag==0){
            se = se + " name LIKE '%"+ "%' ";
        }
        try {
            ResultSet results = statement.executeQuery(se);

            while(results.next()){
                profile p= new profile();
                p.setUsername(results.getString("username"));
                p.setDob(results.getString("dob"));
                p.setGender(results.getString("gender"));
                p.setName(results.getString("name"));
                re.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(se);
        for(Object p:re){
            profile k=(profile)p;
            System.out.println(k.getUsername());

        }
        if (!dob.equals("#")){
            idob=Integer.parseInt(dob);
            Calendar now = Calendar.getInstance();   // Gets the current date and time
            int year = now.get(Calendar.YEAR);
            idob=year-idob;
            for (Object p : re){
                profile k=(profile)p;
                int s= k.getDob().length();
                System.out.println(k.getDob().substring(s-4,s));
                int j = Integer.parseInt(k.getDob().substring(s-4,s));
                if(j>idob-3&&j<idob+3){
                    ans.add(k);
                }

            }
        }
        else {
            ans=re;
        }
        for(Object p:ans){
            profile k=(profile)p;
            System.out.println(k.getUsername());

        }
        return ans;

    }




}

