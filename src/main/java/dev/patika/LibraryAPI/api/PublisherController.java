package dev.patika.LibraryAPI.api;

import dev.patika.LibraryAPI.business.abstracts.IPublisherService;
import dev.patika.LibraryAPI.core.config.modelMapper.IModelMapperService;
import dev.patika.LibraryAPI.core.result.Result;
import dev.patika.LibraryAPI.core.result.ResultData;
import dev.patika.LibraryAPI.core.utilies.ResultHelper;
import dev.patika.LibraryAPI.dto.request.publisher.PublisherSaveRequest;
import dev.patika.LibraryAPI.dto.request.publisher.PublisherUpdateRequest;
import dev.patika.LibraryAPI.dto.response.CursorResponse;
import dev.patika.LibraryAPI.dto.response.publisher.PublisherResponse;
import dev.patika.LibraryAPI.entities.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("v1/publishers")
public class PublisherController {

    private final IPublisherService publisherService;
    private final IModelMapperService modelMapper;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)   // publisheresponse veriyoruz kullanıcadan request olarak almak istiyoruz
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        Publisher savePublisher = this.modelMapper.forRequest().map(publisherSaveRequest, Publisher.class);
        this.publisherService.save(savePublisher);
        return ResultHelper.created(this.modelMapper.forResponse().map(savePublisher, PublisherResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> get(@PathVariable("id") int id){
        Publisher publisher = this.publisherService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(publisher, PublisherResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<Publisher> publisherPage = this.publisherService.cursor(page,pageSize);
        Page<PublisherResponse> publisherResponsePage = publisherPage
                .map(publisher -> this.modelMapper.forResponse().map(publisher, PublisherResponse.class));
        return ResultHelper.curse(publisherResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)   // publisheresponse veriyoruz kullanıcadan request olarak almak istiyoruz
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {
        Publisher updatePublisher = this.modelMapper.forRequest().map(publisherUpdateRequest, Publisher.class);
        this.publisherService.update(updatePublisher);
        return ResultHelper.success(this.modelMapper.forResponse().map(updatePublisher, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.publisherService.delete(id);
        return ResultHelper.ok();
    }



}
