package com.eclesiastelucien.com.lucienstore.modules.category;

import com.eclesiastelucien.com.lucienstore.commons.exceptions.ForbiddenResourceException;
import com.eclesiastelucien.com.lucienstore.commons.exceptions.ResourceNotFoundException;
import com.eclesiastelucien.com.lucienstore.commons.utils.BaseServiceImpl;
import com.eclesiastelucien.com.lucienstore.modules.category.models.Category;
import com.eclesiastelucien.com.lucienstore.modules.user.enums.UserRoleEnum;
import com.eclesiastelucien.com.lucienstore.modules.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void create(String name) {
        User connectedUser = this.authenticatedUser();
        if (connectedUser.getRole().equals(UserRoleEnum.ADMINISTRATOR)) {
            Category category = new Category(name);
            this.categoryRepository.save(category);
            return;
        }
        throw new ForbiddenResourceException();
    }

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    public Category findById(Long id) throws ResourceNotFoundException {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " was not found."));
    }

    public void update(Long id, String name) {
        User connectedUser = this.authenticatedUser();
        if (connectedUser.getRole().equals(UserRoleEnum.ADMINISTRATOR)) {
            Category category = this.findById(id);
            category.setName(name);
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
