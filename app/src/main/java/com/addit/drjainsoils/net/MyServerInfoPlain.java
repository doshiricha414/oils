package com.addit.drjainsoils.net;

import com.google.firebase.iid.FirebaseInstanceId;
import com.addit.drjainsoils.javaClass.AppConfig;


import org.json.JSONException;
import org.json.JSONObject;

public class MyServerInfoPlain {

    public static final String SERVER_URL_1 = "http://" + "clientworksarea.com/kapaya/";
    public static final String asmx = "WebService.asmx/";
    public static final String RegisterAPI = SERVER_URL_1 + asmx + "UserRegistration";
    public static final String IMAGE_DOWNLOAD_LOCATION = SERVER_URL_1+"Upload/EventImage/";
    public static final String ChangePasswordAPI=SERVER_URL_1 + asmx + "ChangePassword";
    public static final String ForgetPasswordAPI=SERVER_URL_1 + asmx + "ForgotPassword";
    public static final String UpdatePhoto=SERVER_URL_1 + asmx + "UpdatePhoto";




    /**
     * GCM Credentials Notifier API
     */
    public static final String GcmCredentialsAPI = SERVER_URL_1 + asmx + "GCMUpdate";  // Gcm Credentials sharer

    /**
     * Login API
     */
    public static final String LoginAPI = SERVER_URL_1 + asmx + "UserLogin";  // Login
    public static final String GetEventAPI = SERVER_URL_1 + asmx + "GetEvent";  // Login
    public static final String GetApplyForJobAPI = SERVER_URL_1 + asmx + "ApplyJob";  //Apply for job
    public static final String GetPostJobAPI = SERVER_URL_1 + asmx + "AddJob";  //Post job
    public static final String GetViewApplicant = SERVER_URL_1 + asmx + "viewapplicant";  //Post job
    public static final String GetRelationAPI=SERVER_URL_1 + asmx + "GetRelation";

    /**
     * Search Contacts API
     */
    public static final String SearchContactsAPI = SERVER_URL_1 + asmx + "GetSearchData";  // Search Contacts
    public static final String SearchDataAPI = SERVER_URL_1 + asmx + "GetSearchData";  // Search Contacts

    /**
     * API to Get Business Categories
     */
    public static final String GetBusinessCatsAPI = SERVER_URL_1 + asmx + "GetIndustry";  // Get Business Categories
    /**
     * API to Get Ftp
     */
    public static final String GetFtpApi = SERVER_URL_1 + asmx + "GetFtpDetail";  // Get Business Categories

    /**
     * API to Get Advertisement links
     */
    public static final String GetAdvertisementAPI = SERVER_URL_1 + asmx + "GetAdvertisment";  // Get Business Categories
    /**
     * Send Referral API
     */
    public static final String SendReferralAPI = SERVER_URL_1 + asmx + "SendGCM";  // Send Referral

    /**
     * Notification API
     */
    public static final String GetNotificationsAPI = SERVER_URL_1 + asmx + "GetAllNotification";  // Notification

    /**
     * Global Notification API
     */
    public static final String GetGlobalNotificationsAPI = SERVER_URL_1 + asmx + "GetGlobalGCMHistory";  // Notification

    /**
     * Request Money Slip
     */
    public static final String MoneySlipAPI = SERVER_URL_1 + asmx + "AddMoneySlip";  // Request Money Slip

    /**
     * Money Slip ACK Acknowledgement
     */
    public static final String MoneySlipAckAPI = SERVER_URL_1 + asmx + "MoneySlipACK";  // Request Money Slip

    /**
     * 1 to 1 Request
     */
    public static final String Do1o1API = SERVER_URL_1 + asmx + "AddOneTOne";  // 1 to 1 Request

    /**
     * 1 to 1 Request Acknowledgement
     */
    public static final String OneToOneAckAPI = SERVER_URL_1 + asmx + "OneToOneACK";  // 1 to 1 Request
    /**
     * FMemberRequest Ack api
     */
    public static final String FamilyMemberRequestACK_API = SERVER_URL_1 + asmx +"AddfamilymemberACK";
    public static final String FamilyMemberRequest = SERVER_URL_1 + asmx +"ADDFamilyMember";
    public static final String FamilyMemberDetails = SERVER_URL_1 + asmx +"GetFamilyMember";

    public static class JsonRequestParams {
        public static final String email = "email";
        public static final String FamilyMemberID = "FamilyMemberID";
        public static final String UserDataID = "UserDataID";
        public static final String UserRegistrationID = "UserRegistrationID";
        public static final String JobID = "JobID";
        public static final String jobid = "jobid";
        public static final String JobTitle =  "JobTitle";
        public static final String JobDesc = "JobDesc";
        public static final String Jobexperience ="Jobexperience";
        public static final String JobLocation = "JobLocation";
        public static final String Responsibility = "Responsibility" ;
        public static final String MemberRegistrationId = "MemberRegistrationId";
        public static final String relationID = "relationID";
        public static final String relationid = "relationid";
        public static final String Relation = "relationname";
        public static final String from_UserDataID = "from_UserDataID";
        public static final String postedBy = "PostedBy";
        public static final String ref_name = "ref_name";
        public static final String ref_email = "ref_email";
        public static final String ref_contact = "ref_contact";
        public static final String ref_work = "ref_work";
        public static final String to_UserDataID = "to_UserDataID";
        public static final String Ref_Name = "Ref_Name";
        public static final String FromName = "FromName";
        public static final String FromContact = "FromContact";

        public static final String EmailID = "EmailID";
        public static final String Emailid = "Emailid";
        public static final String UserName = "UserName";
        public static final String MoileNo = "MobileNo";
        public static final String Password = "Password";
        public static final String GCM_RegId = "GCM_RegId";
        public static final String GCM_Token = "GCM_Token";

        public static final String BName = "BName";
        public static final String CategoryID = "CategoryID";

        public static final String amt = "amt";
        public static final String refName = "refName";
        public static final String remark = "remark";
        public static final String date = "date";
        public static final String time = "time";
        public static final String venue = "venue";
        public static final String Do1to1ID = "ID";
        public static final String moneySlipID = "JobID";
        public static final String accept = "accept";
        public static final String AppVer = "AppVer";
        public static final String Gender = "Gender";
        public static final String DOB = "DOB";
        public static final String MobileNo = "MobileNo";
        public static final String IndustryID = "IndustryID";
        public static final String PhotoURL = "PhotoUrl";
        public static final String Name = "Name";
        public static final String City = "City";
        public static final String PIN = "PIN";
        public static final String Address = "Address";
        public static final String MaritialStatus = "MaritialStatus";
        public static final String BloodGrp = "BloodGrp";
        public static final String WorkType = "WorkType";
        public static final String status = "Status";
        public static final String OldPassword = "OldPassword";
        public static final String NewPassword = "NewPassword";

        public static String LoginJson(String UserName1, String pwd) throws JSONException {
           JSONObject j=new JSONObject();
            j.put(UserName, UserName1);
            j.put(Password, pwd);
            j.put(GCM_RegId, FirebaseInstanceId.getInstance().getToken());
            j.put(GCM_Token, FirebaseInstanceId.getInstance().getToken());
            /*j.put(AppVer, AppConfig.CURRENT_APP_VERSION);*/
            return  j.toString();
        }

        public static String UploadPhoto(String ID, String ImageName) throws JSONException {
            JSONObject j=new JSONObject();
            j.put(UserRegistrationID, ID);
            j.put(PhotoURL, ImageName);
            /*j.put(AppVer, AppConfig.CURRENT_APP_VERSION);*/
            return  j.toString();
        }


        public static String ForgetPassword(String Email, String Phone) throws JSONException {
            JSONObject j=new JSONObject();
            j.put(EmailID, Email);
            j.put(MoileNo, Phone);
            j.put(GCM_RegId, FirebaseInstanceId.getInstance().getToken());
            j.put(GCM_Token, FirebaseInstanceId.getInstance().getToken());
            /*j.put(AppVer, AppConfig.CURRENT_APP_VERSION);*/
            return  j.toString();
        }


        public static String RegisterJson(String id,String name, String email, String dob, String mobileno, String gender, String martialStatus, String blood_grp, String i_do, String city, String address, String catID, String bName,String photo) throws JSONException {
            String j="";
           try {
             j = new JSONObject()
                       .put(UserRegistrationID,id)
                       .put(MobileNo, mobileno)
                       .put(Name, name)
                       .put(EmailID, email)
                       .put(DOB, dob)
                       .put(Gender, gender)
                       .put(MaritialStatus, martialStatus)
                       .put(BloodGrp, blood_grp)
                       .put(WorkType, i_do)
                       .put(City, city)
                       .put(Address, address)
                       .put(IndustryID, catID)
                       .put(BName, bName)
                       .put(PhotoURL, photo)
                       .put(AppVer, AppConfig.CURRENT_APP_VERSION)
                       .toString();
           }catch(Exception e){

               String h=e.toString();
           }
            return j;
        }
        public static String SearchDataJason(String name, String mobileno, String blood_grp,String city, String address, String catID, String bName) throws JSONException {
            return new JSONObject()
                    .put(MobileNo, mobileno)
                    .put(Name, name)
                    .put(BloodGrp, blood_grp)
                    .put(City, city)
                    .put(Address, address)
                    .put(IndustryID, catID)
                    .put(WorkType, bName)
                    .put(AppVer, AppConfig.CURRENT_APP_VERSION)
                    .toString();
        }
    }

    public class JsonResponseParams {

        public static final String FamilyMemberID = JsonRequestParams.FamilyMemberID ;
        public static final String FullName = "FullName" ;
        public static final String FTPIpAddress = "IpAddress" ;
        public static final String  FTPUserName= "UserName" ;
        public static final String  FTPPassword= "Password" ;
        public static final String FtpImagePath = "imagepath" ;
        public static final String Relation1 = "Relation";
        public static final String JobID = JsonRequestParams.JobID ;
        public static final String jobid = JsonRequestParams.jobid ;
        public static final String JobTitle = JsonRequestParams.JobTitle ;
        public static final String JobDesc = JsonRequestParams.JobDesc ;
        public static final String Jobexperience = JsonRequestParams.Jobexperience ;
        public static final String JobLocation = JsonRequestParams.JobLocation ;
        public static final String Responsibility = JsonRequestParams.Responsibility ;
        public static final String records = "records";
        public static final String Status = "Status";
        public static final String Success = "Success";
        public static final String relationID = JsonRequestParams.relationID;
        public static final String relationid= JsonRequestParams.relationid;
        public static final String Relation = JsonRequestParams.Relation;
        public static final String Failed = "Failed";
        public static final String UserDataID = JsonRequestParams.UserDataID;
        public static final String UserRegistrationID = JsonRequestParams.UserRegistrationID;
        public static final String Name = "FullName";
        public static final String MobileNo = "MobileNo";
        public static final String Contact = "Contact";
        public static final String BusinessName = "CompanyName";
        public static final String Email = "Email";
        public static final String EmailID = JsonRequestParams.EmailID;
        public static final String Emailid = JsonRequestParams.Emailid;
        public static final String UserImage = "PhotoUrl";
        public static final String CategoryID = "CategoryID";
        public static final String CategoryName = "CategoryName";
        public static final String CatImage = "CatImage";
        public static final String referred_by_name = "referred_by_name";
        public static final String Ref_Name = JsonRequestParams.Ref_Name;
        public static final String Ref_Email = "Ref_Email";
        public static final String Ref_Contact = "Ref_Contact";
        public static final String Ref_Work = "Ref_Work";
        public static final String from_UserDataID = JsonRequestParams.from_UserDataID;
        public static final String FromName = JsonRequestParams.FromName;
        public static final String FromContact = JsonRequestParams.FromContact;
        public static final String Tiltle = "Tiltle";
        public static final String Title = "Title";
        public static final String Description = "Description";
        public static final String SendDate = "SendDate";
        public static final String eventdesc = "eventdesc";
        public static final String eventid = "eventid";
        public static final String eventtitle = "eventtitle";
        public static final String imageurl = "imageurl";
        public static final String  eventdate= "eventdate";
        public static final String  imageName= "imageName";

        public static final String work = "work";
        public static final String amt = JsonRequestParams.amt;
        public static final String remark = JsonRequestParams.remark;
        public static final String date = JsonRequestParams.date;
        public static final String time = JsonRequestParams.time;
        public static final String venue = JsonRequestParams.venue;
        public static final String Do1to1ID = JsonRequestParams.Do1to1ID;
        public static final String FromEmail = "FromEmail";
        public static final String sendDate = "sendDate";
        public static final String moneySlipID = JsonRequestParams.moneySlipID;
        public static final String accept = JsonRequestParams.accept;

        public static final String GcmType = "FcmType";
        public static final String GcmType_referral = "referral";
        public static final String GcmType_global = "global";
        public static final String GcmType_money_slip = "money-slip";
        public static final String GcmType_money_slip_ack = "money-slip-ack";
        public static final String GcmType_do1to1 = "do1to1";
        public static final String GcmType_do1to1_ack = "do1to1-ack";
        public static final String IndustryName = "IndustryName";
        public static final String IndustryID = "IndustryID";
        public static final String MemberRegistrationID="MemberRegistrationID";
        public static final String Relationname="Relationname";
        public static final String canViewJob="Isposted";
    }
}
