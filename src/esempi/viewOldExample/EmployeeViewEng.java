package esempi.viewOldExample;

import java.util.ArrayList;

import com.generation.model.Employee;

public class EmployeeViewEng implements EmployeeView
{
    @Override
    public String render(Employee e)
    {
        StringBuilder res  = new StringBuilder();
        res
        .append("Hello i'm an employee with id: ")
        .append(e.getId())
        .append(" , with name ")
        .append(e.getName())
        .append(" , with surname ")
        .append(e.getSurname());

        return res.toString();
    }

    @Override
    public String render(ArrayList<Employee> l)
    {
        StringBuilder res = new StringBuilder();

        for(Employee e:l)
            res.append(renderOne(e)).append("\n");

        return res.toString();
    }

}
