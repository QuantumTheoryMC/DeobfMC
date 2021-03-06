package qtmc.lib.deobfuscate.tasks;

import qtmc.lib.deobfuscate.DeobfuscateExtension;
import org.apache.commons.io.FileUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.net.URL;

public class DownloadMappingTask extends DefaultTask {
    public static File mappings;
    @TaskAction
    public void doTask() {
        try {
            DeobfuscateExtension extension = getProject().getExtensions().getByType(DeobfuscateExtension.class);
            if (extension.getSrgLocation() == null) {
                mappings = new File(CheckDestinationTask.destination, "client.srg");
                URL url = new URL(extension.getDownloadSrgLocation());
                FileUtils.copyURLToFile(url, mappings);
                return;
            }
            mappings = getProject().file(extension.getSrgLocation());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
