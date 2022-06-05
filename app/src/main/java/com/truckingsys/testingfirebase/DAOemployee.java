package com.truckingsys.testingfirebase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOemployee {
    private DatabaseReference databasereference;
    public DAOemployee()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databasereference = db.getReference(employee.class.getSimpleName());
    }

    public Task<Void> add(employee emp)
    {

        return databasereference.push().setValue(emp);
    }
}
