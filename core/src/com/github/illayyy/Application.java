package com.github.illayyy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.github.illayyy.material.Entity;
import com.github.illayyy.material.Material;
import com.github.illayyy.util.MathUtils;

import java.awt.*;

public class Application extends ApplicationAdapter {
    private static final int WORLD_WIDTH = 400;
    private static final int WORLD_HEIGHT = 400;
    private static final float BRUSH_SIZE_RATE = (float) (WORLD_HEIGHT + WORLD_WIDTH) / 1000;
    private World world;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Material material = Material.SAND;
    private float brushSize = Math.max((float) (WORLD_HEIGHT + WORLD_WIDTH) / 100, 1);

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
        Point mouseLocation = getMouseLocation();
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int y = 0; y < WORLD_HEIGHT; y++) {
            for (int x = 0; x < WORLD_WIDTH; x++) {
                if (MathUtils.isInsideCircle(x, y, mouseLocation.x, mouseLocation.y, brushSize)) {
                    shapeRenderer.setColor(material.getEntity().getColor());
                } else {
                    shapeRenderer.setColor(matrix[y][x].getColor());
                }

                shapeRenderer.rect(x, y, 1, 1);
            }
        }

        shapeRenderer.end();
    }

    private void input() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            draw();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            material = material.next();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.EQUALS)) {
            brushSize += BRUSH_SIZE_RATE;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.MINUS)) {
            brushSize = Math.max(brushSize - BRUSH_SIZE_RATE, 1);
        }
    }

    private Point getMouseLocation() {
        Vector3 vector3 = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        return new Point(Math.round(vector3.x), Math.round(vector3.y));
    }

    private void draw() {
        Point mouseLocation = getMouseLocation();
        int top = (int) Math.ceil(mouseLocation.y - brushSize);
        int bottom = (int) Math.floor(mouseLocation.y + brushSize);
        int left = (int) Math.ceil(mouseLocation.x - brushSize);
        int right = (int) Math.floor(mouseLocation.x + brushSize);

        for (int y = top; y < bottom; y++) {
            for (int x = left; x < right; x++) {
                if (MathUtils.isInsideCircle(x, y, mouseLocation.x, mouseLocation.y, brushSize)) {
                    world.setCell(x, y, material.getEntity());
                }
            }
        }
    }
}
