package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @Repository를 함으로써 컴포넌틑 스캔의 대상이됨
// + 프로젝트의 크기가 작아서 따로 레포 패키지를 만들지않음
// + db와 연결될 시 추가적인 연결 필요
@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    // 실무에서는 해쉬맵 사용 안됨 -> 멀티 스레드 환경 고려 : 사용하고 싶으면 concurrentHashMap 사용
    // static 사용함
    private static long sequence = 0L; //static

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        // arraylist로 한 번 감싸서 반환 -> 안전하게
        return new ArrayList<>(store.values());
    }

    // 정석으로하려면 아이템에 아이디가 사용이 안되니까 사용되는 아이들만 모아서 따로 클래스로 만드는게 맞음
    // ex) ItemParamDTO 이런 느낌으로 만들어놓는게
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    // test 용도
    public void clearStore(){
        store.clear();
    }
}
