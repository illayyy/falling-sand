package com.github.illayyy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.github.illayyy.material.Material;
import com.github.illayyy.material.Sand;

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
    }

    @Override
    public void render() {
        input();
        display();
        world.step();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    private void display() {
        Material[][] matrix = world.getMatrix();

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int y = 0; y < WORLD_HEIGHT; y++) {
            for (int x = 0; x < WORLD_WIDTH; x++) {
                Material material = matrix[y][x];
                shapeRenderer.setColor(material.getColor());
                shapeRenderer.rect(x, y, 1, 1);
            }
        }

        shapeRenderer.end();
    }

    private void input() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            Vector3 coords = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            world.setCell((int) coords.x, (int) coords.y, new Sand());
        }
    }
}
