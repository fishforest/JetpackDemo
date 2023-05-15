package com.fish.jetpackdemo.fragment.dialogFragment;

import androidx.lifecycle.ViewModel;

public class FFVM extends ViewModel {
   private String school = "default school";

   public String getSchool() {
      return school;
   }

   public void setSchool(String school) {
      this.school = school;
   }

   @Override
   protected void onCleared() {
      super.onCleared();
   }
}
