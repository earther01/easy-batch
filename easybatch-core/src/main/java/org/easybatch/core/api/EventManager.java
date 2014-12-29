package org.easybatch.core.api;

import org.easybatch.core.api.event.global.BatchProcessEventListener;
import org.easybatch.core.api.event.record.*;

import java.util.Set;

/**
 *
 */
public interface EventManager {

    /**
     * @param batchProcessEventListener
     */
    public void addBatchProcessListener(final BatchProcessEventListener batchProcessEventListener);

    /**
     *
     * @param recordReaderEventListener
     */
    public void addRecordReaderListener(final RecordReaderEventListener recordReaderEventListener);

    /**
     *
     * @param recordFilterEventListener
     */
    public void addRecordFilterEventListener(final RecordFilterEventListener recordFilterEventListener);

    /**
     *
     * @param recordMapperEventListener
     */
    public void addRecordMapperListener(final RecordMapperEventListener recordMapperEventListener);

    /**
     *
     * @param recordValidatorEventListener
     */
    public void addRecordValidatorEventListener(final RecordValidatorEventListener recordValidatorEventListener);

    /**
     *
     * @param recordProcessorEventListener
     */
    public void addRecordProcessorEventListener(final RecordProcessorEventListener recordProcessorEventListener);

    /**
     * Called before the whole batch starts.
     */
    public void fireBeforeBatchStart();

    /**
     * Called after the whole batch ends.
     */
    public void fireAfterBatchEnd();

    /**
     * Called on any exception thrown in the whole process.
     * @param t The exception
     */
    public void fireOnBatchException(Throwable t);

    /**
     * Called before the reader opens
     */
    public void fireBeforeReaderOpen();

    /**
     * Called after the reader has been opened.
     */
    public void fireAfterReaderOpen();

    /**
     * Called before a record gets read.
     */
    public void fireBeforeRecordRead();

    /**
     * Called after the record was read and returned.
     * @param record The record that has been read.
     */
    public void fireAfterRecordRead(final Record record);

    /**
     * Called on exception while reading the record
     * @param throwable The exception happened
     */
    public void fireOnRecordReadException(Throwable throwable);

    /**
     * Called before the record is passed to the filter.
     * @param record The record to be filtered
     */
    public void fireBeforeFilterRecord(final Record record);

    /**
     * Called after the record was filtered.
     * @param record Can be null in case the record was filtered.
     * @param filterRecord Record identified for filtering?
     */
    public void fireAfterFilterRecord(final Record record, boolean filterRecord);

    /**
     * Called before the record is passed into the mapper.
     * @param record The record that is going to be mapped.
     */
    public void fireBeforeMapRecord(final Record record);

    /**
     * Called after the mapping process.
     * @param record The record that came in.
     * @param mapResult The result that came out.
     */
    public void fireAfterMapRecord(final Record record, final Object mapResult);

    /**
     * Called before the mapped record gets validated.
     * @param mappedRecord the mapped record.
     */
    public void fireBeforeValidateRecord(final Object mappedRecord);

    /**
     * Called after the record is validated.
     * @param validatedRecord The validated record.
     * @param validationErrors The set of validation errors that came out.
     */
    public void fireAfterValidateRecord(final Object validatedRecord, final Set<ValidationError> validationErrors);

    /**
     * Called before the record gets processed.
     * @param record The record to be processed.
     */
    public void fireBeforeProcessRecord(final Object record);

    /**
     * Called after the processing is done.
     *
     * Do not use this method to do additional work on the record or the result.
     *
     * @param record The record that has been processed.
     * @param processResult The processing result, if any. This can be a null reference.
     */
    public void fireAfterRecordProcessed(final Object record, final Object processResult);
}
