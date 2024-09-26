package hello.crudservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemRepositoryTest {

    ItemRepository itemRepository=new ItemRepository();

    @AfterEach
    public void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        Item item =new Item("userA",10000,20);

        Item savedItem1 = itemRepository.save(item);
        Item savedItem2=itemRepository.findById(item.getId());

        assertThat(savedItem1).isEqualTo(savedItem2);
    }

    @Test
    void findAll(){
        Item item1 =new Item("userA",10000,20);
        Item item2 =new Item("userB",20000,30);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> items =itemRepository.findAll();

        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1,item2);
    }

    @Test
    void update(){
        Item item1 =new Item("userA",10000,20);

        Item savedItem = itemRepository.save(item1);
        Long savedItemId = savedItem.getId();

        Item item2 =new Item("userB",20000,30);
        itemRepository.update(savedItemId,item2);

        Item findItem = itemRepository.findById(savedItemId);

        assertThat(savedItem.getItemName()).isEqualTo(findItem.getItemName());
        assertThat(savedItem.getPrice()).isEqualTo(findItem.getPrice());
        assertThat(savedItem.getQuantity()).isEqualTo(findItem.getQuantity());
    }
}
