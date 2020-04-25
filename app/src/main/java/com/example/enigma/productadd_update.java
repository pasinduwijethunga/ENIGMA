package com.example.enigma;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.InputMismatchException;


public class productadd_update extends Fragment {

    EditText txtname,txttitle,txtrating,txtprice,txt_ID;
    Button btninsert, btnclear,btnUpdate,btndelete,btnViewAll,btnsearch,btnViewupdate;
    View v;
    DatabaseHelperProduct db;
    String examCenterValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_productadd_update, container, false);

        txt_ID = v.findViewById(R.id.txtCusName);
        txtname =v.findViewById(R.id.txtProID);
        txttitle = v.findViewById(R.id.txtDesc);
        txtrating = v.findViewById(R.id.txtRate);
        txtprice = v.findViewById(R.id.txtPrice);

        btninsert = v.findViewById(R.id.btnInsert);
        btnclear = v.findViewById(R.id.btnClear);
        btndelete = v.findViewById(R.id.btnDelete);
        btnUpdate = v.findViewById(R.id.btnUpdate);
        btnViewAll = v.findViewById(R.id.btnViewAll);
        btnsearch= v.findViewById(R.id.btnsearch);

        db = new DatabaseHelperProduct(this.getActivity());

        //samitha insert
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String productname = txtname.getText().toString();
                String producttitle = txttitle.getText().toString();
                String productrating = txtrating.getText().toString();
                String productprice = txtprice.getText().toString();





                if(productname.length() != 0){

                    if(producttitle.length() != 0){

                        if(productrating.length() != 0){

                            if(productprice.length() != 0){

                                try{


                                    Product dto = new Product();
                                    dto.setTitle(productname);
                                    dto.setPrice(Double.parseDouble(productprice));
                                    dto.setRating(Double.parseDouble(productrating));
                                    dto.setShortdesc(producttitle);



                                    boolean i = db.addInfo(dto);

                                    if(i){

                                        Toast.makeText(getActivity(), "Product Added Successfully",Toast.LENGTH_LONG).show();
                                        txtname.setText(" ");
                                        txttitle.setText(" ");
                                        txtrating.setText(" ");
                                        txtprice.setText(" ");

                                    }else{

                                        Toast.makeText(getActivity(), "Fail to Add Product",Toast.LENGTH_LONG).show();

                                    }

                                }catch (InputMismatchException s){

                                    Toast.makeText(getActivity(), "Invalid Type",Toast.LENGTH_LONG).show();

                                }catch (NumberFormatException s){

                                    Toast.makeText(getActivity(), "Invalid data type entered",Toast.LENGTH_LONG).show();


                                }

                            }else{

                                Toast.makeText(getActivity(), "Insert price",Toast.LENGTH_LONG).show();


                            }

                        }else{

                            Toast.makeText(getActivity(), "Insert rate",Toast.LENGTH_LONG).show();


                        }
                    }else {

                        Toast.makeText(getActivity(), "Insert Description",Toast.LENGTH_LONG).show();


                    }
                }else{

                    Toast.makeText(getActivity(), "Insert name",Toast.LENGTH_LONG).show();



                }



            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_ID.setText(" ");
                txtname.setText(" ");
                txttitle.setText(" ");
                txtrating.setText(" ");
                txtprice.setText(" ");

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productid = txt_ID.getText().toString();
                String productname = txtname.getText().toString();
                String producttitle = txttitle.getText().toString();
                String productrating = txtrating.getText().toString();
                String productprice = txtprice.getText().toString();

                if(productid.length() != 0){

                    if(productname.length() != 0){

                        if(producttitle.length() != 0){

                            if(productrating.length() != 0){

                                if(productprice.length() != 0){

                                    boolean isUpdate = db.updateData(productid,productname,producttitle,productrating,productprice);

                                    if(isUpdate == true){
                                        Toast.makeText(getActivity(), "Data Updated",Toast.LENGTH_LONG).show();
                                        txt_ID.setText(" ");
                                        txtname.setText(" ");
                                        txttitle.setText(" ");
                                        txtrating.setText(" ");
                                        txtprice.setText(" ");

                                    }else{
                                        Toast.makeText(getActivity(), "Data not Updated",Toast.LENGTH_LONG).show();
                                    }

                                }else{
                                    Toast.makeText(getActivity(), "Insert price",Toast.LENGTH_LONG).show();
                                }
                            }else{
                                Toast.makeText(getActivity(), "Insert rate",Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Insert Description",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getActivity(), "Insert name",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Insert id",Toast.LENGTH_LONG).show();
                }
            }
        });


        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRow = db.deleteData(txt_ID.getText().toString());

                if(deleteRow > 0){
                    Toast.makeText(getActivity(), "Row Deleted Successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Select the correct Id to Delete",Toast.LENGTH_LONG).show();
                }

            }
        });


        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getAllData();
                if(res.getCount() == 0){
                    //Toast.makeText(getActivity(),"No Delivery to show",Toast.LENGTH_LONG).show();
                    showMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID: " +res.getString(0)+ "\n");
                    buffer.append("Name: " +res.getString(1)+ "\n");
                    buffer.append("Title: " +res.getString(2)+ "\n");
                    buffer.append("Rating: " +res.getString(3)+ "\n");
                    buffer.append("Price: " +res.getString(4)+ "\n\n");

                }

                showMessage("Data",buffer.toString());
            }

        });




        return v;
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }


}
