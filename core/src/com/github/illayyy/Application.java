package com.github.illayyy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.github.illayyy.material.Entity;
import com.github.illayyy.material.Material;

public class Application extends ApplicationAdapter {
    private static final int WORLD_WIDTH = 50;
    private static final int WORLD_HEIGHT = 50;
    private World world;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Material material = Material.SAND;

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
        Entity[][] matrix = world.getMatrix();
        Vector3 coords = getMouseCoords();
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int y = 0; y < WORLD_HEIGHT; y++) {
            for (int x = 0; x < WORLD_WIDTH; x++) {
                Entity entity = matrix[y][x];
                shapeRenderer.setColor(entity.getColor());
                shapeRenderer.rect(x, y, 1, 1);
            }
        }

        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(material.getEntity().getColor());
        shapeRenderer.rect((int) coords.x, (int) coords.y, 1, 1);
        shapeRenderer.end();
    }

    private void input() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            Vector3 coords = getMouseCoords();
            world.setCell((int) coords.x, (int) coords.y, material.getEntity());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            material = material.next();
        }
    }

    private Vector3 getMouseCoords() {
        return camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    }
}
