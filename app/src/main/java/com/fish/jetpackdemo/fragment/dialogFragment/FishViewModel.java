package com.fish.jetpackdemo.fragment.dialogFragment;

import androidx.lifecycle.ViewModel;

public class FishViewModel extends ViewModel {
   private String name = "hello";

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   protected void onCleared() {
      super.onCleared();
   }
}
