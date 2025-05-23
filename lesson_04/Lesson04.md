1. ДЗ
2. volatile
3. Atomic

----
volatile
--
volatile — это модификатор переменной, который используется для обеспечения корректной работы с переменными в
многопоточной среде.

Когда переменная объявлена с модификатором volatile, это означает две вещи:

--happens-before:  
Изменения, сделанные в этой переменной одним потоком, 
будут немедленно видны другим потокам. 
Без volatile потоки могут работать с локальными копиями переменной, 
и изменения могут не синхронизироваться с основной (в heap).

--Запрет на кэширование и оптимизации:  
Компилятор и процессор не будут оптимизировать доступ к volatile переменной,
например, не сохранят её в регистр или кэш.

**Важно:**  
volatile не обеспечивает атомарности.  
Например, операции ++, --, x = x + 1 не будут атомарными даже с volatile.
Для более сложной синхронизации лучше использовать synchronized, Atomic* классы.
volatile можно использовать когда переменная читается/пишется одним потоком, 
а читается — другим. Если два и более потока осуществляют 
запись значения volatile не подходит!

---

Atomic
--
Atomic-классы — обеспечивает безопасную работу с переменными в многопоточной 
среде без использования блокировок (synchronized).  
Они находятся в пакете java.util.concurrent.atomic.

Atomic-классы предоставляют атомарные (непрерываемые) операции над 
переменными:  
--чтение  
--запись  
--инкремент/декремент  
--сравнение и замена (compareAndSet)  
Они используют низкоуровневые команды процессора для достижения 
высокой производительности.

***Потокобезопасный counter:***
```
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
private AtomicInteger counter = new AtomicInteger(0); // создали объкт Atomic

    public void increment() {
        counter.incrementAndGet(); // атомарное ++
    }

    public int get() {
        return counter.get(); // безопасное чтение
    }
}
```

Atomic работает существенно быстрее synchronized.  
Atomic всегда можно заменить на synchronized, но не наоборот!  
Если критическая секция представляет собой несколько 
взаимосвязанных операций - atomic не подходит!