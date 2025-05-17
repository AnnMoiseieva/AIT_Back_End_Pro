package option1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Document {
    private final int id;
    private final String name;
    private final List<Document> relatedDocs = new ArrayList<>();

    public Document(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void addRelatedDocument(Document doc) {
        relatedDocs.add(doc);
    }

    public void edit() {
        // создаем сортированный список всех документов
        List<Document> toLock = new ArrayList<>(relatedDocs);
        toLock.add(this);
        toLock.sort(Comparator.comparingInt(Document::getId));


        System.out.println(Thread.currentThread().getName() + " редактирует " + name);
        for (Document doc : toLock) {
            synchronized (doc) {
                System.out.println(Thread.currentThread().getName() + " редактирует связанный документ " + doc.name);
                // имитация редактирования
            }
        }
    }



    public String getName() {
        return name;
    }

    public List<Document> getRelatedDocs() {
        return relatedDocs;
    }
}