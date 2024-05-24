package com.generation.view.review;

import java.util.List;

import com.generation.model.Review;
import com.generation.view.IView;

public class ReviewVIewItaImpl implements IView
{
    private static ReviewVIewItaImpl singleInstance ;

    public static ReviewVIewItaImpl getSingleton()
    {
        //lazy inizialization
        if(singleInstance==null)
            singleInstance = new ReviewVIewItaImpl();


        return singleInstance;
    }


    private ReviewVIewItaImpl(){}

    @Override
    public String render(Object o)
    {
        StringBuilder res  = new StringBuilder();
        Review e = (Review)o;
        res
        .append("Review id: ")
        .append(e.getId());

        return res.toString();
    }

    @Override
    public String render(List<Object> l)
    {
        StringBuilder res = new StringBuilder();

        for(Object e:l)
            res.append(render(e)).append("\n");

        return res.toString();
    }

}
