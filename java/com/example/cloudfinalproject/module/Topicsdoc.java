package com.example.cloudfinalproject.module;

public class Topicsdoc {

    String topic_name;
        String topic_video;

        public Topicsdoc(String topic_name, String topic_video) {
            this.topic_name = topic_name;
            this.topic_video = topic_video;
        }

        public String getTopic_name() {
            return topic_name;
        }

        public void setTopic_name(String topic_name) {
            this.topic_name = topic_name;
        }

        public String getTopic_video() {
            return topic_video;
        }

        public void setTopic_video(String topic_video) {
            this.topic_video = topic_video;
        }
    }

