package de.gtec.psdata.service.upload;

import de.gtec.psdata.storage.entity.Frame;

public interface UploadService {

    boolean uploadAll(Frame[] dps);

}
