package org.alibek.ls10.servlet;

import lombok.extern.java.Log;
import org.alibek.ls10.entity.Car;
import org.alibek.ls10.service.DbService;
import org.alibek.ls10.service.DbServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

//Model-View-Controller
@Log
@WebServlet(name = "Servlet", value = "/index")
public class CarsServlet extends HttpServlet {
    //между базами данных и сервлетами должна быть прослойка, сервисы.Сервис знает сервлет и бд. Сервлет не знает бд

    private DbService dbService;
    //принцип солид инжект интерфейса, в дальнейшем можно менять реализацию а интерфейс остается

    private Logger logger = Logger.getLogger(CarsServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Car> allCars = dbService.getAllCars();

        log.info("CARS=========" + allCars.toString());

        request.setAttribute("cars", allCars);
        request.getRequestDispatcher("index.jsp").forward(request,response);
        //отправляем клиента через диспатчер, вызываем диспатчер просим найди index.jsp страницу и отправляем туда клиента
        //гарантия того что все обьекты которые мы берем как реквест они будут сохранены
        //в этом случае клиенту отдаем jsp в post запросах нет
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //response.sendRedirect("/"); //перенаправление на страницу которая уже есть

        String name = request.getParameter("name");
        Double engineVolume = Double.valueOf(request.getParameter("engine_volume"));
        Double price = Double.valueOf(request.getParameter("price"));
        Car car = new Car(null, name, engineVolume, price);
        dbService.addCar(car);
        response.sendRedirect("/index");



    }

    @Override
    public void init() throws ServletException { //запускается первым сразу после инициализации сервлета
        dbService = new DbServiceImpl();

        //родительский метод который идет от самого httpServlet при инициализации своего рода конструкторы
    }
    //destroy() нужен для того чтобы освободить connection к базе данных во время разрущения сервлета
}
//Post запрос не должен возвращать HTML

// Get запрос возвращает HTML
//покупки не делать через get запрос

//maven сборщик проектов который в себе компанует. Принимает в себе зависимости и в качестве библиотек себе подтягивает
//также делает автоматизацию установки, deploying,  packaging
//pom-project object model




/*Внутренние обьекты которые доступны у скреплетов
* В самой их главной сути, просто используются для заполнения сайта*/