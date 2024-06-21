package com.github.illayyy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Application extends ApplicationAdapter {
    private static final int WORLD_WIDTH = 50;
    private static final int WORLD_HEIGHT = 50;
    private World world;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    @Override
    public void create() {
        world = new World(WORLD_WIDTH, WORLD_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, WORLD_WIDTH, WORLD_HEIGHT);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                draw(screenX, screenY);

                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                draw(screenX, screenY);

                return true;
            }
        });
    }

    @Override
    public void render() {
        int[][] matrix = world.getMatrix();

        camera.update();
        ScreenUtils.clear(Color.BLACK);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int y = 0; y < WORLD_HEIGHT; y++) {
            for (int x = 0; x < WORLD_WIDTH; x++) {
                if (matrix[y][x] == 1) {
                    shapeRenderer.rect(x, y, 1, 1);
                }
            }
        }

        shapeRenderer.end();
        world.step();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    private void draw(int x, int y) {
        Vector3 coords = camera.unproject(new Vector3(x, y, 0));
        world.setCell((int) coords.x, (int) coords.y, 1);
    }
}
