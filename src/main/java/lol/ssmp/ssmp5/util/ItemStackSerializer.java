package lol.ssmp.ssmp5.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ItemStackSerializer {

    public static String serializeContents(ItemStack[] contents) {
        JSONArray jsonArray = new JSONArray();

        for (ItemStack itemStack : contents) {
            if (itemStack != null) {
                JSONObject itemObject = new JSONObject();
                itemObject.put("type", itemStack.getType().name());
                itemObject.put("amount", itemStack.getAmount());

                jsonArray.add(itemObject);
            } else {
                jsonArray.add(null);
            }
        }

        return jsonArray.toJSONString();
    }

    public static ItemStack[] deserializeContents(String serializedContents) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(serializedContents);

            ItemStack[] contents = new ItemStack[jsonArray.size()];

            for (int i = 0; i < jsonArray.size(); i++) {
                Object itemObject = jsonArray.get(i);

                if (itemObject instanceof JSONObject) {
                    JSONObject jsonItem = (JSONObject) itemObject;
                    Material material = Material.getMaterial((String) jsonItem.get("type"));
                    int amount = ((Long) jsonItem.get("amount")).intValue();

                    contents[i] = new ItemStack(material, amount);
                } else {
                    contents[i] = null;
                }
            }

            return contents;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}