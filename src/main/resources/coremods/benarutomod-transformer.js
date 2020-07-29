function initializeCoreMod() {
	return {
		'player_init_hook': {
			'target': {
				'type': 'CLASS',
				'name': 'net.minecraft.entity.player.PlayerEntity'
			},
			'transformer': function(classNode) {
			    log("Patching PlayerEntity...");
                patch({
                    obfName: "",
                    name: "<init>",
                    desc: "(Lnet/minecraft/world/World;Lcom/mojang/authlib/GameProfile;)V",
                    patch: patch_PlayerEntity_Init
                }, classNode);
				return classNode;
			}
		},
		'creative_inventory_slot_hook': {
		    'target': {
                'type': 'CLASS',
                'name': 'net.minecraft.client.gui.screen.inventory.CreativeScreen'
            },
            'transformer': function(classNode) {
                log("Patching CreativeScreen...");
                patch({
                    obfName: "func_147050_b",
                    name: "setCurrentCreativeTab",
                    desc: "(Lnet/minecraft/item/ItemGroup;)V",
                    patch: patch_CreativeScreen_setCurrentCreativeTab
                }, classNode);
                return classNode;
            }
		},
		'creative_inventory_action_fix': {
		    'target': {
		        'type': 'CLASS',
		        'name': 'net.minecraft.network.play.ServerPlayNetHandler'
		    },
		    'transformer': function(classNode) {
		        log("Patching ServerPlayerNetHandler...");
		        patch({
                    obfName: "func_147344_a",
                    name: "processCreativeInventoryAction",
                    desc: "(Lnet/minecraft/network/play/client/CCreativeInventoryActionPacket;)V",
                    patch: patch_ServerPlayerNetHandler_processCreativeInventoryAction
                }, classNode);
                return classNode;
		    }
		}
	};
}

function findMethod(methods, entry) {
    var length = methods.length;
    for(var i = 0; i < length; i++) {
        var method = methods[i];
        if((method.name.equals(entry.obfName) || method.name.equals(entry.name)) && method.desc.equals(entry.desc)) {
            return method;
        }
    }
    return null;
}

function patch(entry, classNode) {
    var method = findMethod(classNode.methods, entry);
    var name = classNode.name.replace("/", ".") + "#" + entry.name + entry.desc;
    if(method !== null) {
        log("Starting to patch: " + name);
        if(entry.patch(method)) {
            log("Successfully patched: " + name);
        } else {
            log("Failed to patch: " + name);
        }
    } else {
        log("Failed to find method: " + name);
    }
}

var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');
var Opcodes = Java.type('org.objectweb.asm.Opcodes');
var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
var InsnNode = Java.type('org.objectweb.asm.tree.InsnNode');
var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
var IntInsnNode = Java.type('org.objectweb.asm.tree.IntInsnNode');
var LabelNode = Java.type('org.objectweb.asm.tree.LabelNode');
var FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');
var TypeInsnNode = Java.type('org.objectweb.asm.tree.TypeInsnNode');
var JumpInsnNode = Java.type('org.objectweb.asm.tree.JumpInsnNode');
var FrameNode = Java.type('org.objectweb.asm.tree.FrameNode');

function patch_PlayerEntity_Init(method) {
    var foundNode = null;
    var instructions = method.instructions.toArray();
    var length = instructions.length;
    for (var i = 0; i < length; i++) {
        var node = instructions[i];
        if(node.getOpcode() == Opcodes.PUTFIELD && (node.name.equals("unused180") || node.name.equals("field_70741_aB"))) {
            foundNode = node;
            break;
        }
    }
    if(foundNode !== null) {
        method.instructions.insert(foundNode, new MethodInsnNode(Opcodes.INVOKESTATIC, "com/benarutomod/tbroski/event/ForgeEventSubscriber", "onPlayerInit", "(Lnet/minecraft/entity/player/PlayerEntity;)V", false));
        method.instructions.insert(foundNode, new VarInsnNode(Opcodes.ALOAD, 0));
        return true;
    }
    return false;
}

function patch_CreativeScreen_setCurrentCreativeTab(method) {
    var foundNode = null;
    var instructions = method.instructions.toArray();
    var length = instructions.length;
    for (var i = 0; i < length; i++) {
        var node = instructions[i];
        if(node.getOpcode() != Opcodes.GETFIELD || !(node.name.equals("destroyItemSlot") || node.name.equals("field_147064_C")))
            continue;
        if(node.getNext() === null || node.getNext().getOpcode() != Opcodes.INVOKEINTERFACE)
            continue;
        if(node.getNext().getNext() === null || node.getNext().getNext().getOpcode() != Opcodes.POP)
            continue;
        foundNode = node.getNext().getNext();
        break;
    }
    if(foundNode !== null) {
        method.instructions.insert(foundNode, new MethodInsnNode(Opcodes.INVOKESTATIC, "com/benarutomod/tbroski/event/ForgeEventSubscriber", "patchCreativeSlots", "(Lnet/minecraft/client/gui/screen/inventory/CreativeScreen$CreativeContainer;)V", false));
        method.instructions.insert(foundNode, new TypeInsnNode(Opcodes.CHECKCAST, "net/minecraft/client/gui/screen/inventory/CreativeScreen$CreativeContainer"));
        method.instructions.insert(foundNode, new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/client/gui/screen/inventory/CreativeScreen", ASMAPI.mapField("field_147002_h"), "Lnet/minecraft/inventory/container/Container;"));
        method.instructions.insert(foundNode, new VarInsnNode(Opcodes.ALOAD, 0));
        return true;
    }
    return false;
}

function patch_ServerPlayerNetHandler_processCreativeInventoryAction(method) {
    var foundNode = null;
    var instructions = method.instructions.toArray();
    var length = instructions.length;
    for (var i = 0; i < length; i++) {
        var node = instructions[i];
        if(node.getOpcode() == Opcodes.INVOKEVIRTUAL && (node.name.equals("getSlotId") || node.name.equals("func_149627_c"))) {
            var nextNode = node.getNext();
            if(nextNode.getOpcode() == Opcodes.BIPUSH && nextNode.operand == 45) {
                foundNode = node;
                break;
            }
        }
    }
    if(foundNode !== null) {
        method.instructions.remove(foundNode.getNext());
        method.instructions.insert(foundNode, new MethodInsnNode(Opcodes.INVOKESTATIC, "com/benarutomod/tbroski/event/ForgeEventSubscriber", "getCreativeSlotMax", "(Lnet/minecraft/entity/player/ServerPlayerEntity;)I", false));
        method.instructions.insert(foundNode, new FieldInsnNode(Opcodes.GETFIELD, "net/minecraft/network/play/ServerPlayNetHandler", ASMAPI.mapField("field_147369_b"), "Lnet/minecraft/entity/player/ServerPlayerEntity;"));
        method.instructions.insert(foundNode, new VarInsnNode(Opcodes.ALOAD, 0));
        return true;
    }
    return false;
}

function log(s) {
    print("[benarutomod-transformer.js] " + s);
}