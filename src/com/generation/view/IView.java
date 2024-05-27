package com.generation.view;

import java.util.List;

public interface IView 
{
    String renderOne(Object o);
    String renderAll(List<Object> o);
}
