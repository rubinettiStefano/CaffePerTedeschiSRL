package com.generation.view;

import java.util.List;

public interface IView 
{
    String render(Object o);
    String render(List<Object> o);
}
