package de.gtec.psdata.service.file;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jml04
 *
 * The MultipartSourceFile class implements the Spring MultipartFile
 * interface and represents a file as a byte array.
 * It can be used to create a MultipartFile instance with a byte array instead of an actual file.
 *
 * -- some comments were generated with ChatGPT --
 */
public class MultipartSourceFile implements MultipartFile {

    // file name
    private final String name;

    // original file name
    private final String originalFilename;

    // content type string
    @Nullable
    private final String contentType;

    // file content as byte array
    private final byte[] content;

    /**

     Creates a new instance of MultipartSourceFile with the given name and content.
     @param name the name of the file
     @param content the content of the file as a byte array
     */
    MultipartSourceFile(String name, @Nullable byte[] content) {
        this(name, "", null, content);
    }
    /**

     Creates a new instance of MultipartSourceFile with the given name and input stream.
     @param name the name of the file
     @param contentStream the content of the file as an input stream
     @throws IOException if an I/O error occurs
     */
    MultipartSourceFile(String name, InputStream contentStream) throws IOException {
        this(name, "", null, FileCopyUtils.copyToByteArray(contentStream));
    }
    /**

     Creates a new instance of MultipartSourceFile with the given name, original filename, content type, and content.
     @param name the name of the file
     @param originalFilename the original filename of the file (may be null)
     @param contentType the content type of the file (may be null)
     @param content the content of the file as a byte array
     */
    MultipartSourceFile(String name, @Nullable String originalFilename, @Nullable String contentType, @Nullable byte[] content) {
        Assert.hasLength(name, "Name must not be empty");
        this.name = name;
        this.originalFilename = originalFilename != null ? originalFilename : "";
        this.contentType = contentType;
        this.content = content != null ? content : new byte[0];
    }
    /**

     Creates a new instance of MultipartSourceFile with the given name, original filename, content type, and input stream.
     @param name the name of the file
     @param originalFilename the original filename of the file (may be null)
     @param contentType the content type of the file (may be null)
     @param contentStream the content of the file as an input stream
     @throws IOException if an I/O error occurs
     */
    MultipartSourceFile(String name, @Nullable String originalFilename, @Nullable String contentType, InputStream contentStream) throws IOException {
        this(name, originalFilename, contentType, FileCopyUtils.copyToByteArray(contentStream));
    }

    /**
     * Returns the name of the file item.
     *
     * @return the name of the file item
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the original filename in the user's file system.
     *
     * @return the original filename
     */
    @Override
    @NonNull
    public String getOriginalFilename() {
        return this.originalFilename;
    }

    /**
     * Returns the content type of the file.
     *
     * @return the content type of the file
     */
    @Override
    @Nullable
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Returns whether the uploaded file item is empty i.e. no file has been chosen.
     *
     * @return true if the uploaded file item is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.content.length == 0;
    }

    /**
     * Returns the size of the file.
     *
     * @return the size of the file
     */
    @Override
    public long getSize() {
        return (long)this.content.length;
    }

    /**
     * Returns the contents of the file as an array of bytes.
     *
     * @return the contents of the file
     * @throws IOException if an I/O error occurs
     */
    @Override
    public byte[] getBytes() throws IOException {
        return this.content;
    }

    /**
     * Returns an InputStream that can be used to read the contents of the file.
     *
     * @return an InputStream that can be used to read the contents of the file
     * @throws IOException if an I/O error occurs
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(this.content);
    }

    /**
     * Transfers the contents of the file to the given destination file.
     *
     * @param dest the destination file
     * @throws IOException if an I/O error occurs
     * @throws IllegalStateException if the destination file already exists or is a directory
     */
    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        FileCopyUtils.copy(this.content, dest);
    }

}
