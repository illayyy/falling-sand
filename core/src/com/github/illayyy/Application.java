package com.github.illayyy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Application extends ApplicationAdapter {
    private static final int WORLD_WIDTH = 100;
    private static final int WORLD_HEIGHT = 100;
    private static final int VIEWPORT_WIDTH = 300;
    private static final int VIEWPORT_HEIGHT = 300;
    private static final float CELL_WIDTH = (float) VIEWPORT_WIDTH / WORLD_WIDTH;
    private static final float CELL_HEIGHT = (float) VIEWPORT_HEIGHT / WORLD_HEIGHT;
    private World world;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    @Override
    public void create() {
        world = new World(WORLD_WIDTH, WORLD_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    @Override
    public void render() {
        int[][] matrix = world.getMatrix();

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int y = 0; y < WORLD_HEIGHT; y++) {
            for (int x = 0; x < WORLD_WIDTH; x++) {
                if (matrix[y][x] == 1) {
                    shapeRenderer.rect(x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
                }
            }
        }

        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
