package com.generation.view;

import java.util.ArrayList;


public interface View 
{
    String render(Object e); //public abstract
    String render(ArrayList<Object> l);
}
