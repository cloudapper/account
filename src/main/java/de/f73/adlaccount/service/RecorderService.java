package de.f73.adlaccount.service;

import java.util.ArrayList;
import java.util.List;

import io.fabric8.kubernetes.api.model.batch.CronJob;
import io.fabric8.kubernetes.api.model.batch.CronJobBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class RecorderService {

    public void startRecorder(String fin) {
    KubernetesClient client = new DefaultKubernetesClient();

    String jobName = "adlrecorder" + fin;
    List<String> args = new ArrayList<>();

    CronJob adlRecorderJob = new CronJobBuilder()
    .withApiVersion("batch/v1beta1")
    .withNewMetadata()
    .withName(jobName)
    .endMetadata()
    .withNewSpec()
    .withSchedule("*/2 * * * *")
    .withNewJobTemplate()
    .withNewSpec()
    .withNewTemplate()
    .withNewSpec()
    .addNewContainer()
    .withName(jobName)
    .withImage("sefose/cloudapp-recorder:latest")
    .withArgs(args)
    .endContainer()
    .withRestartPolicy("Never")
    .endSpec()
    .endTemplate()
    .endSpec()
    .endJobTemplate()
    .endSpec()
    .build();
    }
}
