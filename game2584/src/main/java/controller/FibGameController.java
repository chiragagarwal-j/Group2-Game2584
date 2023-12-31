package controller;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import model.Game2584;

public class FibGameController {
	private Game2584 model;

	public FibGameController() {
		model = new Game2584();
	}

	public void processGet(IWebExchange webExchange, TemplateEngine templateEngine, HttpServletResponse res)
			throws ServletException, IOException {
		final WebContext ctx = new WebContext(webExchange);
		var out = res.getWriter();
		int[][] gameBoard = model.getIntBoard();
		int index = 1;
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				if (gameBoard[i][j] == 0) {
					ctx.setVariable("tile" + index, " ");
				} else {
					ctx.setVariable("tile" + index, gameBoard[i][j]);
				}
				index++;
			}
		}
		ctx.setVariable("board", model.toSingleArray());
		ctx.setVariable("score", model.getScore());
		templateEngine.process("play2584", ctx, out);
	}

	public void processPost(IWebExchange webExchange, TemplateEngine templateEngine, HttpServletResponse res)
			throws ServletException, IOException {
		var request = webExchange.getRequest();
		String key = request.getParameterValue("control");
		switch (key) {
		case "U":
			model.up();
			model.spawn();
			break;
		case "D":
			model.down();
			model.spawn();
			break;
		case "L":
			model.left();
			model.spawn();
			break;
		case "R":
			model.right();
			model.spawn();
			break;
		case "RS":
			model=new Game2584();
			break;
		}


		res.sendRedirect(request.getRequestPath());
	}
}
