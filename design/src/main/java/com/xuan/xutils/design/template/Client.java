package com.xuan.xutils.design.template;

/**
 * @author xuan
 * @since 2023/2/20
 */
public class Client {

    public static void main(String[] args) {

        Game game = new GameMovie();
        System.out.println("----看电影----");
        game.play();
        game = new GameCamping();
        System.out.println("----去露营----");
        game.play();
    }
}
