package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.UserService;

public class AddItemToBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;
    @Inject
    private static UserService userService;

    private static Long userId = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String itemId = req.getParameter("item_id");

        Item item = itemService.get(Long.valueOf(itemId));
        User user = userService.get(userId);
        Bucket bucket = bucketService.getAll()
                .stream()
                .filter(b -> b.getUserId().equals(user.getId()))
                .findFirst()
                .orElse(bucketService.create(new Bucket(user)));
        bucketService.addItem(bucket, item);

        resp.sendRedirect(req.getContextPath() + "/shop");
    }
}