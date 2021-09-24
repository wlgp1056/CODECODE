package project_controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller2 {

	String run(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;

}