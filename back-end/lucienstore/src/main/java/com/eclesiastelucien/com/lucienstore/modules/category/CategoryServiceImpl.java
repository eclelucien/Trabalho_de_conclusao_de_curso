package com.eclesiastelucien.com.lucienstore.modules.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eclesiastelucien.com.lucienstore.commons.exceptions.ForbiddenResourceException;
import com.eclesiastelucien.com.lucienstore.commons.exceptions.ResourceNotFoundException;
import com.eclesiastelucien.com.lucienstore.commons.utils.BaseServiceImpl;
import com.eclesiastelucien.com.lucienstore.commons.utils.FileUploadUtil;
import com.eclesiastelucien.com.lucienstore.modules.category.dtos.requests.CategoryRequest;
import com.eclesiastelucien.com.lucienstore.modules.category.models.Category;
import com.eclesiastelucien.com.lucienstore.modules.user.enums.UserRoleEnum;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;

@Service
public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    public void create(CategoryRequest categoryRequest, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("file cannot be null or empty");
        }
        User connectedUser = this.authenticatedUser();
        if (connectedUser.getRole().equals(UserRoleEnum.ADMINISTRATOR)) {
            String fileName = this.fileUploadUtil.upload(file);
            Category category = new Category(categoryRequest);
            category.setImage(fileName);
            this.categoryRepository.save(category);
            return;
        }
        throw new ForbiddenResourceException();

    }

    public List<Category> findAll(int page, int limit) {
        return this.categoryRepository.findAll();
    }

    public Category findById(Long id) throws ResourceNotFoundException {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " was not found."));
    }

    public void update(Long id, CategoryRequest categoryRequestDto) {
        User connetedUser = this.authenticatedUser();
        if (connetedUser.getRole().equals(UserRoleEnum.ADMINISTRATOR)) {
            Category category = this.findById(id);
            category.setName(categoryRequestDto.getName());
            category.setTags(categoryRequestDto.getTags());
            this.categoryRepository.save(category);
            return;
        }
        throw new ForbiddenResourceException();
    }

    public void remove(Long id) {
        User connectedUser = this.authenticatedUser();
        if (connectedUser.getRole().equals(UserRoleEnum.ADMINISTRATOR)) {
            this.findById(id);
            this.categoryRepository.deleteById(id);
            return;
        }
        throw new ForbiddenResourceException();
    }
}
