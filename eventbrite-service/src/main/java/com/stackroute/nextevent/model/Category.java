package com.stackroute.nextevent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
   private String categoryId;
   private String name;
   private List<SubCategory> subCategory;
   private int ageRestriction;

   @Override
   public String toString() {
      return "Category{" +
              "categoryId='" + categoryId + '\'' +
              ", name='" + name + '\'' +
              ", subCategory=" + subCategory +
              ", ageRestriction=" + ageRestriction +
              '}';
   }
}
