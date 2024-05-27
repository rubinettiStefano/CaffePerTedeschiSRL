package com.generation.view.review;

import java.util.List;

import com.generation.model.Review;
import com.generation.view.IView;

public class ReviewViewItaImpl implements IView
{
    private static ReviewViewItaImpl singleInstance ;

    public static ReviewViewItaImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new ReviewViewItaImpl();


        return singleInstance;
    }


    private ReviewViewItaImpl(){}

    @Override
    public String renderOne(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Review e = (Review)o;
        res
        .append("Review id: ")
        .append(e.getId());

        return res.toString();
    }

    @Override
    public String renderAll(List<Object> l)
    {
        StringBuilder res = new StringBuilder();

        for(Object e:l)
            res.append(renderOne(e)).append("\n");

        return res.toString();
    }

}
