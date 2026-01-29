package QazCodeMentoring_Week3_Task5;

import java.math.BigDecimal;
import java.util.List;

public class ImmutableModel {
}
final class Money{
    private final BigDecimal amount;

    Money(BigDecimal amount) {
        if(amount==null || amount.signum()==-1){
            throw new IllegalArgumentException();
        }
        this.amount = amount;
    }
    public Money add(Money other){
        return new Money(this.amount.add(other.amount));
    }
    public Money multiply(int count){
        return new Money((this.amount).multiply(new BigDecimal(count)));
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    @Override
    public boolean equals(Object obj) {
       if(this == obj) return true;
       if (obj == null || obj.getClass() != getClass()) return false;
       return amount.compareTo(((Money) obj).amount) == 0;
    }

    @Override
    public int hashCode() {
        return amount.stripTrailingZeros().hashCode();
    }
}

final class OrderItem{
    private final String product;
    private final  Money price;
    private final  int quantity;

    public OrderItem(String product, Money price, int quantity){
        if(product == null || product.isEmpty() || price == null || quantity <=0) throw new IllegalArgumentException();
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public Money cost(){
        return price.multiply(quantity);
    }
}

final class Order{
    private final String id;
    private final List<OrderItem> items;

    Order(String id, List<OrderItem> items) {
        this.id = id;
        this.items = List.copyOf(items);
    }

    public Money total(){
        Money itog = new Money(BigDecimal.ZERO);
        for(OrderItem item: items){
            itog = itog.add(item.cost());
        }
        return itog;
    }
}