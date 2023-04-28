package nad.Analysis;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.FloatBinding;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Activity {
    //private String id, activity, predecessor, optimistic, mostLikely, pessimistic, time;
    private SimpleIntegerProperty id;
    private SimpleFloatProperty optimistic, mostLikely, pessimistic, duration;
    private SimpleStringProperty activity, predecessor;

    public Activity() {
    }

    public Activity(int id, String activity, String predecessor,
                    float optimistic, float mostLikely, float pessimistic, float duration) {
        this.id = new SimpleIntegerProperty(id);
        this.activity = new SimpleStringProperty(activity);
        this.predecessor = new SimpleStringProperty(predecessor);
        this.optimistic = new SimpleFloatProperty(optimistic);
        this.mostLikely = new SimpleFloatProperty(mostLikely);
        this.pessimistic = new SimpleFloatProperty(pessimistic);
        this.duration = new SimpleFloatProperty(duration);

        FloatBinding addition = (FloatBinding) Bindings.add((this.optimisticProperty()
                        .add(this.pessimisticProperty()).divide(6)),
                (this.mostLikelyProperty().multiply(4)).divide(6));
        this.durationProperty().bind(addition);
    }

    public Activity(int id, String activity, String predecessor, float duration) {
        this(id, activity, predecessor, 0, 0, 0, duration);
    }

    public int getId() {
        return id.get();
    }

    public String getActivity() {
        return activity.get();
    }

    public String getPredecessor() {
        return predecessor.get();
    }

    public void setPredecessor(String predecessor) {
        this.predecessor.set(predecessor);
    }

    public void setDuration(float duration) {
        this.duration.set(duration);
    }

    public float getOptimistic() {
        return optimistic.get();
    }

    public SimpleFloatProperty optimisticProperty() {
        return optimistic;
    }

    public void setOptimistic(float optimistic) {
        this.optimistic.set(optimistic);
    }

    public float getMostLikely() {
        return mostLikely.get();
    }

    public SimpleFloatProperty mostLikelyProperty() {
        return mostLikely;
    }

    public void setMostLikely(float mostLikely) {
        this.mostLikely.set(mostLikely);
    }

    public float getPessimistic() {
        return pessimistic.get();
    }

    public SimpleFloatProperty pessimisticProperty() {
        return pessimistic;
    }

    public void setPessimistic(float pessimistic) {
        this.pessimistic.set(pessimistic);
    }

    public float getDuration() {
        return duration.get();
    }

    public SimpleFloatProperty durationProperty() {
        return duration;
    }

}
